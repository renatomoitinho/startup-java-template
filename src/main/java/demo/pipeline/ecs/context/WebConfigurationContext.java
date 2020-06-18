package demo.pipeline.ecs.context;

import demo.pipeline.ecs.util.CustomHeaderLocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfigurationContext implements WebMvcConfigurer {

    @Bean
    @Primary
    public AcceptHeaderLocaleResolver localeResolver() {
        return new CustomHeaderLocaleResolver();
    }

    @Bean
    @Primary
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:messages/message");
        source.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        source.setDefaultLocale(CustomHeaderLocaleResolver.PT_BR);
        return source;
    }

    @Bean
    @Primary
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
