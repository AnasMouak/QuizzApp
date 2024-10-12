package spring.quizz_app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "questionText", nullable = false)
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false, foreignKey = @ForeignKey(name = "fk_quiz"))
    @JsonBackReference
    private Quiz quiz;

    public Question() { }

    public Question(String questionText, Quiz quiz) {
        this.questionText = questionText;
        this.quiz = quiz;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }
}