package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.quizz_app.model.Score;
import spring.quizz_app.model.User;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUser(User user);
}
