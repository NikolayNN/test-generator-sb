package my.nhorushko.testgeneratorsb.testgeneratorsb.model;

import java.util.List;
import java.util.Objects;

public final class QuestionBlock {

    private final Question question;
    private final List<Answer> answers;

    public QuestionBlock(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {

        answers.add(answer);
    }

    @Override
    public String toString() {
        return "QuestionBlock{" +
                "question=" + question +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionBlock that = (QuestionBlock) o;
        return Objects.equals(question, that.question) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(question, answers);
    }
}
