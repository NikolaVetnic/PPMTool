package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.User;
import com.nv.ppmtool.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserEntityRepository userEntityRepository;

    public User saveUser(User newUser) {

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // username has to be unique (exception)
        // make sure that password and confirmPassword match
        // don't persist or show the confirmPassword

        return userEntityRepository.save(newUser);
    }
}
