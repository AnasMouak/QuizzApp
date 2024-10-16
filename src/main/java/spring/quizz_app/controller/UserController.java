package spring.quizz_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.quizz_app.service.MyUserDetailsService;
import spring.quizz_app.service.UserService;
import spring.quizz_app.util.JwtUtil;
import spring.quizz_app.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;


    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
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
public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
    try {
        // Authenticate the user with username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Load user details and generate the JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        // Prepare response with the JWT token
        Map<String, String> response = new HashMap<>();
        response.put("message", "User logged in successfully");
        response.put("jwtToken", jwt);

        return ResponseEntity.ok(response); // Return 200 OK with the token
    } catch (BadCredentialsException e) {
        // Handle invalid credentials specifically
        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return 401 Unauthorized
    } catch (Exception e) {
        // Handle any other exceptions
        Map<String, String> response = new HashMap<>();
        response.put("message", "An error occurred during login");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Return 500 Internal Server Error
    }
}

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}