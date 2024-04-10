package com.bfl.ui.auth.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bfl.ConfigProperties;
import com.bfl.Constants;
import com.bfl.alerts.EmailService;
import com.bfl.alerts.EmailTemplateDTO;
import com.bfl.ui.auth.dao.RoleDao;
import com.bfl.ui.auth.dao.UserDao;
import com.bfl.ui.auth.dto.UserRegistrationDto;
import com.bfl.ui.auth.model.Role;
import com.bfl.ui.auth.model.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao; 

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

    public User findByEmail(String email){
    	return userDao.findByEmail(email);
    }

    public List<Role> getAllRoles(){
        return roleDao.findAll();
    }
    
    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setTemporaryPassword(true);
        user.setRoles(Arrays.asList(new Role(registration.getRoleId())));
        return userDao.save(user);
    }
    
    public void sendUserRegistrationNotification(User user, String applicationURI){
    	try {
    		EmailTemplateDTO emailDto = new EmailTemplateDTO();
    		Map<String, Object> vars = new HashMap<String, Object>();
    		vars.put("name", user.getFirstName().concat(" ").concat(user.getLastName()));
    		vars.put("userName", user.getEmail());
    		vars.put("password", ConfigProperties.getInstance().getConfigValue(Constants.DEFAULT_PASSWORD_FOR_NEW_USER));
    		vars.put("applicationURL", applicationURI);
    		emailDto.setVariables(vars);
    		emailDto.setSubject(ConfigProperties.getInstance().getConfigValue(Constants.USER_REGISTRATION_NOTIFICATION_EMAIL_SUBJECT));
    		emailDto.setEmailTemplate("userRegistrationNotificationEmailTemplate.html");
    		String[] recipients = {user.getEmail()};
    		emailDto.setRecepients(recipients);

    		emailService.sendEmail(emailDto);
    	} catch (Exception e) {
    		logger.error("Error occurred while sending user registration email alert to: "+user.getEmail());
    	}
    }
    
    public User updateUser(User user){
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email.toLowerCase());
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
