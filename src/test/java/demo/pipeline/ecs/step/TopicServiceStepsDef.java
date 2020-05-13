package demo.pipeline.ecs.step;

import demo.pipeline.ecs.context.ApplicationTestContext;
import demo.pipeline.ecs.model.Topic;
import demo.pipeline.ecs.repository.TopicRepository;
import demo.pipeline.ecs.service.TopicService;
import io.cucumber.java8.Pt;
import org.assertj.core.util.Lists;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = ApplicationTestContext.class)
public class TopicServiceStepsDef implements Pt {

    @Autowired
    public TopicServiceStepsDef(TopicRepository repository, TopicService topicService) {

        AtomicReference<Flux<Topic>> resultsTopics = new AtomicReference<>();
        Dado("que exista o topico {string} criado", (String topic) -> {
            List<Topic> topics = Lists.newArrayList(new Topic() {{
                setId(1L);
                setMessage(topic);
            }});

            Mockito.doReturn(topics).when(repository).findAll();
        });

        Quando("o usuario solicitar pelos topicos", () -> resultsTopics.set(topicService.find()));

        Entao("o topico {string} devera ser apresentado", (String topic) -> {
            assertNotNull(resultsTopics.get());
            StepVerifier.create(resultsTopics.get())
                    //.expectNextCount(1)
                    .expectNextMatches(t-> topic.equals(t.getMessage()))
                    .verifyComplete();
        });

        Dado("que o solicite a criacao de um topico {string}", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
            // throw new io.cucumber.java8.PendingException();
        });

        Quando("executar a criacao", () -> {
            // Write code here that turns the phrase above into concrete actions
            //throw new io.cucumber.java8.PendingException();
        });

        Entao("o topico {string} devera ser criado", (String string) -> {
            // Write code here that turns the phrase above into concrete actions
        });

        Dado("que exista o topico {string} com o id {string}", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
        });

        Quando("solicicar a remocao", () -> {
            // Write code here that turns the phrase above into concrete actions
        });

        Entao("o topico {string} com o id {string} foi removido", (String string, String string2) -> {
            // Write code here that turns the phrase above into concrete actions
        });
    }
}