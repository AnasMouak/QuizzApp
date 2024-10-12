package spring.quizz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.quizz_app.model.Answer;
import spring.quizz_app.model.Question;
import spring.quizz_app.repository.AnswerRepository;
import spring.quizz_app.repository.QuestionRepository;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer addAnswerToQuestion(Long questionId, Answer answer) {
        Question question = questionRepository.findById(questionId).orElse(null);
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

}