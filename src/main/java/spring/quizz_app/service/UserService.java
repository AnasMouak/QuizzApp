package spring.quizz_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.quizz_app.repository.UserRepository;
import spring.quizz_app.model.User;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean loginUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        return userFromDB != null && passwordEncoder.matches(user.getPassword(), userFromDB.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
