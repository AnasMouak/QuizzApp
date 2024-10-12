package spring.quizz_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.quizz_app.service.UserService;
import spring.quizz_app.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**@PostMapping("/register")
    public User RegisterUser(@RequestBody User user) {
        return userService.saveUser(user);
    }**/

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody User user) {
        boolean isUserLoggedIn = userService.loginUser(user);
        if (isUserLoggedIn) {
            User userFromDB = userService.getUserByUsername(user.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("message", "User logged in successfully");
            response.put("username", userFromDB.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}