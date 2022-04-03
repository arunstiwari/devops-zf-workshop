package com.tekmentors.javareferenceprj.controller;

import com.tekmentors.javareferenceprj.model.User;
import com.tekmentors.javareferenceprj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> fetchUsers() {
        log.info("In fetchUsers ");
        List<User> Users = userService.fetchUsers();
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Long id){
        log.info(" In findUserById , {}",id);
        User User = userService.findUser(id);
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createNewUser(@RequestBody User User) {
        log.info("In create new User : {}", User);
        User newUser = userService.createUser(User);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
