package spring.quizz_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "answer")
    private String answerText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "FK_question"))
    private Question question;

    public Answer() { }

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answerText = answer;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}