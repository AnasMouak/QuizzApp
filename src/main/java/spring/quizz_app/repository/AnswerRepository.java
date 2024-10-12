package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.quizz_app.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
