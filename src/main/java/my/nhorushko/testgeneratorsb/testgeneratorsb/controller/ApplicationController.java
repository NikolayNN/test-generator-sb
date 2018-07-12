package my.nhorushko.testgeneratorsb.testgeneratorsb.controller;

import my.nhorushko.testgeneratorsb.testgeneratorsb.model.QuestionBlock;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.User;
import my.nhorushko.testgeneratorsb.testgeneratorsb.model.UserTest;
import my.nhorushko.testgeneratorsb.testgeneratorsb.service.AppLocalization;
import my.nhorushko.testgeneratorsb.testgeneratorsb.service.TestGeneratorService;
import my.nhorushko.testgeneratorsb.testgeneratorsb.view.View;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

    private final View view;

    private final TestGeneratorService testGeneratorService;

    private final AppLocalization appLocalization;

    public ApplicationController(View view, TestGeneratorService testGeneratorService,
                                 AppLocalization appLocalization) {
        this.view = view;
        this.testGeneratorService = testGeneratorService;
        this.appLocalization = appLocalization;
    }

    public void run(String lang) {

        appLocalization.setLocale(lang);

        view.write(appLocalization.getLocalizationMessage("enter.name"));
        String userName = view.readNotBlankString();

        view.write(appLocalization.getLocalizationMessage("enter.surname"));
        String userSurname = view.readNotBlankString();

        UserTest userTest = testGeneratorService.generate(new User(userName, userSurname));

        for (QuestionBlock questionBlock : userTest.getQuestionBlocks()) {

            view.write(questionBlock);

            int chosenAnswerNumber = view.readInt(questionBlock.getAnswers().size() - 1);

            userTest.addDecision(questionBlock, questionBlock.getAnswers().get(chosenAnswerNumber));
        }

        view.write(appLocalization.getLocalizationMessage("test.result",
                new String[]{String.valueOf(userTest.calculateCorrectAnswers())}));
    }
}
