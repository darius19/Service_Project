package com.service.svp.service.service;

import com.service.svp.service.dto.RegisterDto;
import com.service.svp.service.model.Role;
import com.service.svp.service.model.User;
import com.service.svp.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterDto registerDto){
        if (userRepository.existsByEmail(registerDto.getEmail())){
            return "Email already exists";
        }
        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.CLIENT);

        userRepository.save(user);

        return "User registered successfully";

    }
}
