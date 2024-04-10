package com.bfl.ui.auth.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bfl.ui.auth.dto.UserRegistrationDto;
import com.bfl.ui.auth.model.Role;
import com.bfl.ui.auth.model.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    
    User updateUser(User user);
    
    List<Role> getAllRoles();
    
    void sendUserRegistrationNotification(User user, String applicationURL);
}
