package spring.quizz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.quizz_app.model.Question;
import spring.quizz_app.model.Quiz;
import spring.quizz_app.repository.QuestionRepository;
import spring.quizz_app.repository.QuizRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }
}