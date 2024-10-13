package spring.quizz_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "answerText", nullable = false)
    private String answerText;

    @Column(name = "isCorrect", nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "fk_question"))
    @JsonBackReference
    private Question question;

    public Answer() { }

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}