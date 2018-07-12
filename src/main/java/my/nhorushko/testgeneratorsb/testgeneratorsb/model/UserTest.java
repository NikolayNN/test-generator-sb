package my.nhorushko.testgeneratorsb.testgeneratorsb.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserTest {

    private final List<QuestionBlock> questionBlocks;

    private final User executor;

    private final Map<QuestionBlock, Answer> decision;

    public UserTest(List<QuestionBlock> questionBlocks, User executor) {
        this.questionBlocks = questionBlocks;
        this.executor = executor;
        this.decision = new HashMap<>();
    }

    public List<QuestionBlock> getQuestionBlocks() {
        return questionBlocks;
    }

    public Map<QuestionBlock, Answer> getDecision() {
        return decision;
    }

    public User getExecutor() {
        return executor;
    }

    public void addDecision(QuestionBlock questionBlock, Answer chosenAnswer){

        decision.put(questionBlock, chosenAnswer);
    }

    public int calculateCorrectAnswers(){

        return (int) decision.entrySet()
                .stream()
                .filter(x -> x.getValue().isCorrect())
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest userTest = (UserTest) o;
        return Objects.equals(questionBlocks, userTest.questionBlocks) &&
                Objects.equals(executor, userTest.executor) &&
                Objects.equals(decision, userTest.decision);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionBlocks, executor, decision);
    }
}
