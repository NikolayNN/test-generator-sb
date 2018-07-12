package my.nhorushko.testgeneratorsb.testgeneratorsb.dao;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;

public interface QuestionBlockParser {
    QuestionBlock convertToQuestionBlock(String line);
}
