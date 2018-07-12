package my.nhorushko.testgeneratorsb.testgeneratorsb.config;

import my.nhorushko.testgeneratorsb.testgeneratorsb.service.AppLocalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class LocalizationConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public AppLocalization appLocalization(@Value("${application.default.localization}") String localeName,
                                           @Value("${path.question.file.en}") String questionsFileEn,
                                           @Value("${path.question.file.ru}") String questionsFileRu) {
        return new AppLocalization(new Locale(localeName), messageSource(), questionsFileEn, questionsFileRu);
    }
}
