package spring.quizz_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.quizz_app.model.Question;
import spring.quizz_app.model.Quiz;
import spring.quizz_app.service.QuestionService;
import spring.quizz_app.service.QuizService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    
    private final QuizService quizService;
    private final QuestionService questionService;

    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
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

}