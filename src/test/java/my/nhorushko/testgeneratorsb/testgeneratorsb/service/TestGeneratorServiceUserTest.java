package my.nhorushko.testgeneratorsb.testgeneratorsb.service;

import my.nhorushko.testgeneratorsb.testgeneratorsb.dao.QuestionBlockReader;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestGeneratorServiceUserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private QuestionBlockReader questionBlockReader;
    private int questionCount;

    private TestGeneratorService testGeneratorService;

    @Before
    public void setUp() {

        questionCount = 5;
        testGeneratorService = new TestGeneratorService(questionBlockReader, questionCount);
    }

    @Test
    public void generate() {

        //given
        final User givenUser = new User("test_name", "test_surname");
        final List<QuestionBlock> givenQuestionBlocks = buildQuestionBlockList(questionCount);
        when(questionBlockReader.readAllQuestions()).thenReturn(givenQuestionBlocks);

        UserTest expectUserTest = new UserTest(givenQuestionBlocks, givenUser);

        //when
        UserTest actualUserTest = testGeneratorService.generate(givenUser);

        //then
        assertEquals(expectUserTest, actualUserTest);
    }

    @Test
    public void generateIfUserIsNull() {

        //given
        User givenUser = null;

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User can't be null");

        //when
        testGeneratorService.generate(givenUser);
    }

    @Test
    public void generateIfQuestionBlockSizeIsZero() {

        //given
        final User givenUser = new User("test_name", "test_surname");
        final List<QuestionBlock> givenQuestionBlocks = buildQuestionBlockList(0);
        when(questionBlockReader.readAllQuestions()).thenReturn(givenQuestionBlocks);

        //then
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Question list is empty");

        //when
        testGeneratorService.generate(givenUser);
    }

    @Test
    public void generateIfReadLessQuestionsBlocks() {

        //given
        final User givenUser = new User("test_name", "test_surname");
        final int givenQuestionsCount = questionCount - 1;
        final List<QuestionBlock> givenQuestionBlocks = buildQuestionBlockList(givenQuestionsCount);
        when(questionBlockReader.readAllQuestions()).thenReturn(givenQuestionBlocks);

        UserTest expectUserTest = new UserTest(givenQuestionBlocks, givenUser);

        //when
        UserTest actualUserTest = testGeneratorService.generate(givenUser);

        //then
        assertEquals(expectUserTest, actualUserTest);
        assertEquals(givenQuestionsCount, actualUserTest.getQuestionBlocks().size());
    }

    @Test
    public void generateIfReadMoreQuestionsBlocks() {

        //given
        final User givenUser = new User("test_name", "test_surname");
        final int givenQuestionsCount = questionCount + 20;
        final List<QuestionBlock> givenQuestionBlocks = buildQuestionBlockList(givenQuestionsCount);
        when(questionBlockReader.readAllQuestions()).thenReturn(givenQuestionBlocks);

        //when
        UserTest actualUserTest = testGeneratorService.generate(givenUser);

        //then
        assertEquals(questionCount, actualUserTest.getQuestionBlocks().size());
    }

    private List<QuestionBlock> buildQuestionBlockList(int count) {

        List<QuestionBlock> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            List<Answer> answers = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                answers.add(new Answer("answer" + i, true));
            }
            result.add(new QuestionBlock(new Question("question" + count), answers));
        }
        return result;
    }
}