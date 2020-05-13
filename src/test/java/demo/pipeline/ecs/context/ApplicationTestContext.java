package demo.pipeline.ecs.context;

import demo.pipeline.ecs.repository.TopicRepository;
import demo.pipeline.ecs.service.TopicService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = {
        "demo.pipeline.ecs.repository",
        "demo.pipeline.ecs.service"
})
public class ApplicationTestContext {

    @Bean
    public TopicRepository topicRepository() {
        return Mockito.mock(TopicRepository.class);
    }

    @Bean
    public Scheduler scheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(1));
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return Mockito.mock(TransactionTemplate.class);
    }

    @Bean
    public TopicService topicService() {
        return new TopicService(topicRepository(), transactionTemplate(), scheduler());
    }
}
