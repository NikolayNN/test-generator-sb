package my.nhorushko.testgeneratorsb.testgeneratorsb.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AppLocalization {

    private Locale locale;

    private final MessageSource messageSource;

    private final String questionsFileEn;
    private final String questionsFileRu;

    public AppLocalization(Locale locale, MessageSource messageSource,
                           String questionsFileEn, String questionsFileRu) {
        this.locale = locale;
        this.messageSource = messageSource;
        this.questionsFileEn = questionsFileEn;
        this.questionsFileRu = questionsFileRu;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(String localeName) {
        this.locale = new Locale(localeName);
    }

    public String getLocalizationMessage(String code, String[] parameters) {
        return messageSource.getMessage(code, parameters, locale);
    }

    public String getLocalizationMessage(String code) {
        return messageSource.getMessage(code, new String[]{}, locale);
    }

    public String getQuestionFilePath() {
        if (locale.getLanguage().equals("en")) {
            return questionsFileEn;
        } else {
            return questionsFileRu;
        }
    }
}
