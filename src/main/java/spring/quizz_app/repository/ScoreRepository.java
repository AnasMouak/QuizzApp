package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.quizz_app.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
