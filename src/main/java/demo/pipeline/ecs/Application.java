package demo.pipeline.ecs;

import demo.pipeline.ecs.context.properties.ProjectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@SuppressWarnings("unused")
@EnableScheduling
@EnableConfigurationProperties({ProjectProperties.class})
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private final ProjectProperties properties;

    public Application(ProjectProperties properties) {
        this.properties = properties;
    }

    @PreDestroy
    public void stop() {
        logger.info("::. {}: {} has been successfully stopped .::",
                properties.getName(), properties.getVersion());
    }

    @Override
    public void run(String... args) {
        logger.info("::. {}: {} started successfully .::", properties.getName(), properties.getVersion());
    }

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
