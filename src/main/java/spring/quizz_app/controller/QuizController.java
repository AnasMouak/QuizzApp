package spring.quizz_app.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import spring.quizz_app.model.Question;
import spring.quizz_app.model.Quiz;
import spring.quizz_app.model.User;
import spring.quizz_app.repository.QuizRepository;
import spring.quizz_app.repository.UserRepository;
import spring.quizz_app.service.QuestionService;
import spring.quizz_app.service.QuizService;
import spring.quizz_app.service.ScoreService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class QuizController {
    
    private final QuizService quizService;
    private final QuestionService questionService;
    private final ScoreService scoreService;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService, ScoreService scoreService, UserRepository userRepository, QuizRepository quizRepository) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.scoreService = scoreService;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.save(quiz);
    }
    
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/quizzes/{quizzId}")
    public Quiz getQuizById(@PathVariable Long quizzId) {
        return quizService.findById(quizzId).orElse(null);
    }

    @PostMapping("/quizzes/{quizId}/questions")
    public Question addQuestionToQuiz(@PathVariable Long quizId, @RequestBody Question question) {
        return questionService.addQuestionToQuiz(quizId, question);
    }

    @GetMapping("/quizzes/{quizzId}/questions")
    public List<Question> getQuestionsByQuizId(@PathVariable Long quizzId) {
        return questionService.getQuestionsByQuizId(quizzId);
    }

    @PostMapping("quizzes/{quizId}/submit")
    public ResponseEntity<?> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody List<Integer> selectedAnswers,
            Principal principal) {
    
        // Fetch the authenticated user from the principal
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
    
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
    
        // Fetch the quiz
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NoSuchElementException("Quiz not found"));
    
        // Calculate the user's score for the quiz
        int scoreValue = scoreService.calculateScore(quizId, selectedAnswers);
    
        // Save the score to the database
        scoreService.saveScore(user, quiz, scoreValue);
    
        // Manually create a map and return as a response
        Map<String, Object> response = new HashMap<>();
        response.put("score", scoreValue);
    
        return ResponseEntity.ok(response);
    }
}