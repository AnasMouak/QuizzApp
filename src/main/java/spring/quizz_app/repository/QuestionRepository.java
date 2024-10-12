package spring.quizz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.quizz_app.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}