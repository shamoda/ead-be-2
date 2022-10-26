package com.sliit.ead.controller;

import com.sliit.ead.model.User;
import com.sliit.ead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(service.createtUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("nic") String nic, @RequestParam("password") String password) {
        return new ResponseEntity<>(service.login(nic, password), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
    }
}
