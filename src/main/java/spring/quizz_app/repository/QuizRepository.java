package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.quizz_app.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
