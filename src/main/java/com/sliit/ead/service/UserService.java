package com.sliit.ead.service;

import com.sliit.ead.model.User;
import com.sliit.ead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User creation
    public User createtUser(User user) {
        return userRepository.save(user);
    }

    // User login
    public User login(String nic, String password ) {
        User user = userRepository.findById(nic).get();
        if (user != null) {
            if (user.getPassword().equalsIgnoreCase(password)) {
                return user;
            }
            return null;
        }
        return null;
    }

    // Get all Users method
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
