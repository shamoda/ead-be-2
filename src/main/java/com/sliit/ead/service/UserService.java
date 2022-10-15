package com.sliit.ead.service;

import com.sliit.ead.model.User;
import com.sliit.ead.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String insertUser(User user) {
        userRepository.save(user);
        return "Registration of user is successful!!";
    }

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

    public List<User> getAllusers() {
        return userRepository.findAll();
    }
}
