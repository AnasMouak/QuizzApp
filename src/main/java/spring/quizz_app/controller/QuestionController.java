package spring.quizz_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.quizz_app.model.Answer;
import spring.quizz_app.service.AnswerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    private final AnswerService answerService;

    @Autowired
    public QuestionController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswerToQuestion(@PathVariable Long questionId, @RequestBody Answer answer) {
        return answerService.addAnswerToQuestion(questionId, answer);
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerService.getAnswersByQuestionId(questionId);
    }
    
}
