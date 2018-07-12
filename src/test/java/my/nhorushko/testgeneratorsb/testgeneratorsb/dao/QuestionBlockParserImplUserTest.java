package my.nhorushko.testgeneratorsb.testgeneratorsb.dao;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.Answer;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.Question;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class QuestionBlockParserImplUserTest {

    private QuestionBlockParser questionBlockParser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        questionBlockParser = new QuestionBlockParserImpl(";", "{Q}", "{true}", "{false}");
    }

    @Test
    public void convertToQuestionBlock() {

        //given
        final String givenString = "{Q}question;{true}answer1;{false}answer2";
        final QuestionBlock expectedQuestionBlock = new QuestionBlock(new Question("question"),
                Arrays.asList(new Answer("answer1", true), new Answer("answer2", false)));

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);

        //then
        assertEquals(expectedQuestionBlock, actualQuestionBlock);
    }

    @Test
    public void convertToQuestionBlockIfStringIsNull() {

        //given
        final String givenString = null;

        //then
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Empty line can't convert to questionBlock");

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);

    }

    @Test
    public void convertToQuestionBlockIfStringIsBlank() {

        //given
        final String givenString = "";

        //then
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Empty line can't convert to questionBlock");

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);
    }

    @Test
    public void convertToQuestionBlockIfStringNotContainsQuestionToken() {

        //given
        final String givenString = "question;{true}answer1;{false}answer2";

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Wrong format. Each line have to starts with question with token {Q}");

        //when
        questionBlockParser.convertToQuestionBlock(givenString);
    }
}