package demo.pipeline.ecs;

import demo.pipeline.ecs.context.properties.ProjectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

@SpringBootApplication
@SuppressWarnings("unused")
@EnableConfigurationProperties({ProjectProperties.class})
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private final ProjectProperties properties;

    public Application(ProjectProperties properties) {
        this.properties = properties;
    }

    @PreDestroy
    public void stop() {
        logger.info("::. {}: {} has been successfully stopped .::", properties.getName(), properties.getVersion());
    }

    @Bean
    public Scheduler jdbcSchedule(@Value("${spring.datasource.hikari.maximum-pool-size}") Integer poolSize) {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(poolSize));
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean
    @Primary
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource  source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:message-validator");
        source.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        return source;
    }

    @Bean
    @Primary
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public void run(String... args) {
        logger.info("::. {}: {} started successfully .::", properties.getName(), properties.getVersion());
    }

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }

}
