package spring.quizz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.quizz_app.model.Quiz;
import spring.quizz_app.model.Score;
import spring.quizz_app.model.User;
import spring.quizz_app.repository.QuizRepository;
import spring.quizz_app.repository.ScoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, ScoreRepository scoreRepository) {
        this.quizRepository = quizRepository;
        this.scoreRepository = scoreRepository;
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

    public List<Score> getQuizzesTakenByUser(User user) {
        return scoreRepository.findByUser(user);
    }


}
