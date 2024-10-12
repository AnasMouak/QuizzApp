package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.quizz_app.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
