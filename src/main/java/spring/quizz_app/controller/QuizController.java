package spring.quizz_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.quizz_app.model.Quiz;
import spring.quizz_app.service.QuizService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }
    
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

}