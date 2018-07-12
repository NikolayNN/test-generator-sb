package my.nhorushko.testgeneratorsb.testgeneratorsb.service;

import my.nhorushko.testgeneratorsb.testgeneratorsb.dao.QuestionBlockReader;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.UserTest;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestGeneratorService {

    private final int questionsCount;

    private final QuestionBlockReader questionBlockReader;

    public TestGeneratorService(QuestionBlockReader questionBlockReader, @Value("${test.questions.count}") int questionsCount) {
        this.questionBlockReader = questionBlockReader;
        this.questionsCount = questionsCount;
    }

    public UserTest generate(User executor) {

        if (executor == null){
            throw new IllegalArgumentException("User can't be null");
        }

        List<QuestionBlock> questionBlocksSource = questionBlockReader.readAllQuestions();

        if (questionBlocksSource.size() == 0) {
            throw new RuntimeException("Question list is empty");
        }

        if (questionBlocksSource.size() <= questionsCount){
            return new UserTest(questionBlocksSource, executor);
        }

        List<QuestionBlock> testQuestionBlocks = new ArrayList<>();

        while (testQuestionBlocks.size() != questionsCount) {

            int randomInt = new Random().nextInt(questionBlocksSource.size());
            testQuestionBlocks.add(questionBlocksSource.get(randomInt));
            questionBlocksSource.remove(randomInt);
        }

        return new UserTest(testQuestionBlocks, executor);
    }
}
