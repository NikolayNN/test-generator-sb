package my.nhorushko.testgeneratorsb.testgeneratorsb.dao;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.Answer;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.Question;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionBlockParserImpl implements QuestionBlockParser {

    private final String csvSeparator;
    private final String questionToken;
    private final String answerTrueToken;
    private final String answerFalseToken;

    public QuestionBlockParserImpl(@Value("${file.parser.token.csv-separator}") String csvSeparator,
                                   @Value("${file.parser.token.text}") String questionToken,
                                   @Value("${file.parser.token.true-text}") String answerTrueToken,
                                   @Value("${file.parser.token.false-text}") String answerFalseToken) {
        this.csvSeparator = csvSeparator;
        this.questionToken = questionToken;
        this.answerTrueToken = answerTrueToken;
        this.answerFalseToken = answerFalseToken;
    }

    @Override
    public QuestionBlock convertToQuestionBlock(String line) {

        if (line == null || line.isEmpty()) {
            throw new RuntimeException("Empty line can't convert to questionBlock");
        }

        String[] questionBlockArray = line.trim().split(csvSeparator);

        QuestionBlock result = new QuestionBlock(questionParser(questionBlockArray[0]), new ArrayList<>());

        for (int i = 1; i < questionBlockArray.length; i++) {

            result.addAnswer(answerParser(questionBlockArray[i]));
        }

        return result;
    }

    private Question questionParser(String line) {

        if (!line.startsWith(questionToken)) {
            throw new IllegalArgumentException("Wrong format. Each line have to starts with question with token " + questionToken);
        }

        return new Question(line.substring(questionToken.length()));
    }

    private Answer answerParser(String line) {

        if (line.startsWith(answerTrueToken)) {
            return new Answer(line.substring(answerTrueToken.length()), true);
        }
        if (line.startsWith(answerFalseToken)) {
            return new Answer(line.substring(answerFalseToken.length()), false);
        }

        throw new IllegalArgumentException("Wrong format. Each answer have to starts with token {true} or {false}");
    }
}
