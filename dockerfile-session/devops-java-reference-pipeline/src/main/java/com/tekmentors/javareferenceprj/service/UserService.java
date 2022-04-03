package com.tekmentors.javareferenceprj.service;

import com.tekmentors.javareferenceprj.model.User;
import com.tekmentors.javareferenceprj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.getById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
