package my.nhorushko.testgeneratorsb.testgeneratorsb.view;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.Answer;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import my.nhorushko.testgeneratorsb.testgeneratorsb.service.AppLocalization;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Service
public class Console implements View {

    private final AppLocalization appLocalization;

    public Console(AppLocalization appLocalization) {
        this.appLocalization = appLocalization;
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(QuestionBlock questionBlock) {

        System.out.println(questionBlock.getQuestion().getText());

        for (int i = 0; i < questionBlock.getAnswers().size(); i++) {
            Answer answer = questionBlock.getAnswers().get(i);
            System.out.println(i + ". " + answer.getText());
        }
    }

    @Override
    public String readNotBlankString() {

        String str = "";

        Scanner scanner = new Scanner(System.in);

        while (str.isEmpty() || str == null) {
            str = scanner.nextLine();
        }

        return str;
    }

    @Override
    public String readSpecifyString(Set<String> stringVariants) {

        String str;

        while (true) {

            str = readNotBlankString();

            if (!stringVariants.contains(str)) {
                write(appLocalization.getLocalizationMessage("error.wrong-command", new String[]{stringVariants.toString()}));
            } else {
                break;
            }
        }
        return str;
    }

    @Override
    public int readInt(int maxValue) {

        int numb = Integer.MIN_VALUE;

        while (numb < 0) {
            try {
                numb = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                write(appLocalization.getLocalizationMessage("error.expect.numb.between",
                        new String[]{String.valueOf(maxValue)}));
                continue;
            }

            if (numb > maxValue || numb < 0) {
                write(appLocalization.getLocalizationMessage("error.expect.numb.between",
                        new String[]{String.valueOf(maxValue)}));
                numb = Integer.MIN_VALUE;
            }
        }
        return numb;
    }
}
