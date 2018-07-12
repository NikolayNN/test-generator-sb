package my.nhorushko.testgeneratorsb.testgeneratorsb.dao;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import my.nhorushko.testgeneratorsb.testgeneratorsb.service.AppLocalization;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionBlockReaderImpl implements QuestionBlockReader {

    private final AppLocalization appLocalization;
    private final QuestionBlockParserImpl questionBlockParserImpl;

    public QuestionBlockReaderImpl(AppLocalization appLocalization, QuestionBlockParserImpl questionBlockParserImpl) {
        this.appLocalization = appLocalization;
        this.questionBlockParserImpl = questionBlockParserImpl;
    }

    public List<QuestionBlock> readAllQuestions() {

        List<QuestionBlock> questionBlocks;

        try (Stream<String> stream = Files.lines(Paths.get(appLocalization.getQuestionFilePath()))) {

            questionBlocks = stream
                    .filter(line -> !line.isEmpty())
                    .map(line -> questionBlockParserImpl.convertToQuestionBlock(line))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return questionBlocks;
    }
}



