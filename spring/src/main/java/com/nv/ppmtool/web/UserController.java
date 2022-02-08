package com.nv.ppmtool.web;

import com.nv.ppmtool.domain.User;
import com.nv.ppmtool.services.MapValidationErrorService;
import com.nv.ppmtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private MapValidationErrorService mapValidationErrorService;
    @Autowired private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        // validate that passwords match

        if (result.hasErrors()) {
            return mapValidationErrorService.mapValidationService(result);
        }

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
