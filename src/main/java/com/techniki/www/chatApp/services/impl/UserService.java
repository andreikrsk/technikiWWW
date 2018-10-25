package com.techniki.www.chatApp.services.impl;

import com.techniki.www.chatApp.exceptions.UserAlreadyExistsException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.User;
import com.techniki.www.chatApp.repositories.UserRepository;
import com.techniki.www.chatApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;


@Service
public class UserService implements IUserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User add(User user) throws UserAlreadyExistsException{
        final Optional<User> byName = userRepository.findByUsername(user.getUsername());
        if (byName.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User: %s already exists!", user.getUsername()));
        }
        user.generateUniqueId();
        return userRepository.insert(user);
    }

    @Override
    public User get(String name) throws UserDontExistException{
        final Optional<User> byName = userRepository.findByUsername(name);
        if(!byName.isPresent()){
            throw new UserDontExistException(String.format("User with name: %s don't exist", name));
        }
        return byName.get();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        final Optional<User> byName = userRepository.findByUsername(name);
        if(!byName.isPresent()){
            throw new UsernameNotFoundException(String.format("User with name: %s don't exist", name));
        }
        return new org.springframework.security.core.userdetails.User(
                byName.get().getUsername(),
                byName.get().getPassword(),
                emptyList());
    }
}
