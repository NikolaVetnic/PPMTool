package com.nv.ppmtool.services;

import com.nv.ppmtool.domain.User;
import com.nv.ppmtool.exceptions.UsernameAlreadyExistsException;
import com.nv.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserRepository userEntityRepository;

    public User saveUser(User newUser) {

        if (userEntityRepository.findByUsername(newUser.getUsername()) != null)
            throw new UsernameAlreadyExistsException(
                    String.format("User with email %s already exists", newUser.getUsername()));

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setConfirmPassword("");

        return userEntityRepository.save(newUser);
    }
}
