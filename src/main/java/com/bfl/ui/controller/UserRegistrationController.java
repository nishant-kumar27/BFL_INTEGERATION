package com.bfl.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bfl.ConfigProperties;
import com.bfl.Constants;
import com.bfl.ui.auth.dto.ChangePasswordDto;
import com.bfl.ui.auth.dto.ResetPasswordDto;
import com.bfl.ui.auth.dto.UserRegistrationDto;
import com.bfl.ui.auth.model.User;
import com.bfl.ui.auth.service.UserService;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    
    @ModelAttribute("roles")
    public List<String> roles() {
    	List<String> roles = new ArrayList<>();
		roles.add(Constants.ROLE_USER);
		roles.add(Constants.ROLE_ADMIN);
        return roles;
    }
    
    @RequestMapping(value={"/registration"}, method=RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @RequestMapping(value={"/processRegistration"}, method=RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	ModelAndView view = new ModelAndView();
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
			view.setViewName("registration");
            return view;
        }
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        userDto.setPassword(ConfigProperties.getInstance().getConfigValue(Constants.DEFAULT_PASSWORD_FOR_NEW_USER));
        User user = userService.save(userDto);
        userService.sendUserRegistrationNotification(user, baseUrl+"/login");
		view.addObject("message","User successfully added. Default Password:<"+ ConfigProperties.getInstance().getConfigValue(Constants.DEFAULT_PASSWORD_FOR_NEW_USER)+ "> Notification has been sent to user.");
		view.setViewName("registration");
        return view;
    }
    
    @RequestMapping(value={"/changePassword"}, method=RequestMethod.GET)
    public ModelAndView displayChangePassword(){
    	ModelAndView view = new ModelAndView();
        view.addObject("changePassword", new ChangePasswordDto());
		view.setViewName("changePassword");
        return view;
    }
    
    @RequestMapping(value={"/processChangePassword"}, method=RequestMethod.POST)
    public ModelAndView processChangePassword(@ModelAttribute("changePassword") @Valid ChangePasswordDto passwordDTO,
                                      BindingResult result){
    	ModelAndView view = new ModelAndView();
    	Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	if(!result.hasErrors() && auth instanceof UserDetails)
    	{
    		User user = userService.findByEmail(((UserDetails)auth).getUsername());
    		if(!passwordEncoder.matches(passwordDTO.getOldPassword(),user.getPassword())) {
    			result.rejectValue("oldPassword", null, "Old Password is wrong");
    		}
    		else {
    			user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
    			userService.updateUser(user);
    		}
    	}
    	if (result.hasErrors()){
 			view.setViewName("changePassword");
             return view;
         }
        view.addObject("message","Password updated successfully.");
		view.setViewName("changePassword");
        return view;
    }
    

    @RequestMapping(value={"/resetPassword"}, method=RequestMethod.GET)
    public ModelAndView displayResetPassword(){
    	ModelAndView view = new ModelAndView();
        view.addObject("resetPassword", new ResetPasswordDto());
		view.setViewName("resetPassword");
        return view;
    }
    
    @RequestMapping(value={"/processResetPassword"}, method=RequestMethod.POST)
    public ModelAndView processResetPassword(@ModelAttribute("resetPassword") @Valid ResetPasswordDto resetPasswordDTO,
                                      BindingResult result) throws IOException{
    	ModelAndView view = new ModelAndView();
    	if(!result.hasErrors())
    	{
    		User user = userService.findByEmail(resetPasswordDTO.getUsername());
    		if(user == null) {
    			result.rejectValue("username", null, "User does not exist");
    		}
    		else {
    			user.setPassword(passwordEncoder.encode(ConfigProperties.getInstance().getConfigValue(Constants.DEFAULT_PASSWORD_FOR_NEW_USER)));
    			userService.updateUser(user);
    		}
    	}
    	if (result.hasErrors()){
 			view.setViewName("resetPassword");
             return view;
         }
        view.addObject("message","Password has been reset to default: "+ ConfigProperties.getInstance().getConfigValue(Constants.DEFAULT_PASSWORD_FOR_NEW_USER));
		view.setViewName("resetPassword");
        return view;
    }
}
