package spring.quizz_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.quizz_app.repository.UserRepository;
import spring.quizz_app.model.User;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean loginUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        return userFromDB != null && userFromDB.getPassword().equals(user.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
