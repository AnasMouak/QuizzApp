package spring.quizz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.quizz_app.model.Quiz;
import spring.quizz_app.model.Score;
import spring.quizz_app.model.User;
import spring.quizz_app.model.Answer;
import spring.quizz_app.model.Question;
import spring.quizz_app.repository.QuizRepository;
import spring.quizz_app.repository.ScoreRepository;

import java.util.List;

import java.util.NoSuchElementException;

@Service
public class ScoreService {

    private final QuizRepository quizRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(QuizRepository quizRepository, ScoreRepository scoreRepository) {
        this.quizRepository = quizRepository;
        this.scoreRepository = scoreRepository;
    }

    // Method to calculate the score based on selected answers
    public Integer calculateScore(Long quizId, List<Integer> selectedAnswerIds) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NoSuchElementException("Quiz not found"));

        int score = 0;

        // Iterate through questions and calculate the score based on correct answers
        List<Question> questions = quiz.getQuestions();
        for (int index = 0; index < questions.size(); index++) {
            Question question = questions.get(index);

            if (index < selectedAnswerIds.size()) {
                Integer selectedAnswerId = selectedAnswerIds.get(index);

                // Check if the selected answer for the question is correct
                boolean isCorrect = question.getAnswers().stream()
                    .filter(Answer::getIsCorrect) // Filter only correct answers
                    .anyMatch(answer -> answer.getId() == selectedAnswerId);

                if (isCorrect) {
                    score++;
                }
            }
        }

        return score;
    }

    // Method to save the calculated score for a user
    public void saveScore(User user, Quiz quiz, int scoreValue) {
        Score score = new Score();
        score.setUser(user);
        score.setQuiz(quiz);
        score.setScore(scoreValue);

        // Save the score in the repository
        scoreRepository.save(score);
    }
}
