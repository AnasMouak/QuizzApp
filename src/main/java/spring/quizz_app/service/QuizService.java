package spring.quizz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.quizz_app.model.Quiz;
import spring.quizz_app.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }
}
