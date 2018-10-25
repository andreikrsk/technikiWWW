package com.techniki.www.chatApp.controllers;

import com.techniki.www.chatApp.exceptions.UserAlreadyExistsException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.User;
import com.techniki.www.chatApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Resource<User>> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User insertedUser = userService.add(user);
        Resource<User> resource = new Resource<>(insertedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest httpServletRequest) throws UserDontExistException {
        System.out.println("User retrieved -> " + httpServletRequest.getUserPrincipal().getName());
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(httpServletRequest.getUserPrincipal().getName()));
    }

    @GetMapping("/")
    public List<User> getUsersList() {
        return userService.getAll();
    }
}
