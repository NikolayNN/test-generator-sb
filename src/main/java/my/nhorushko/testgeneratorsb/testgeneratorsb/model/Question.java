package my.nhorushko.testgeneratorsb.testgeneratorsb.model;

import java.util.Objects;

public final class Question {

    private final String text;

    public Question(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text);
    }
}
