package my.nhorushko.testgeneratorsb.testgeneratorsb.model;

import java.util.Objects;

public final class Answer {

    private final String text;
    private final boolean isCorrect;

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return isCorrect == answer.isCorrect &&
                Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, isCorrect);
    }
}
