package my.nhorushko.testgeneratorsb.testgeneratorsb.view;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;

import java.util.Set;

public interface View {

    void write(String message);

    void write(QuestionBlock questionBlock);

    String readNotBlankString();

    String readSpecifyString(Set<String> stringVariants);

    int readInt(int maxValue);
}
