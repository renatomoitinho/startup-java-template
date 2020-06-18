//package demo.pipeline.ecs.step;
//
//import demo.pipeline.ecs.context.ApplicationTestContext;
//import demo.pipeline.ecs.model.Topic;
//import demo.pipeline.ecs.repository.TopicRepository;
//import demo.pipeline.ecs.service.TopicService;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.Entao;
//import io.cucumber.java.pt.Quando;
//import io.cucumber.spring.CucumberContextConfiguration;
//import org.assertj.core.util.Lists;
//import org.junit.Ignore;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.ContextConfiguration;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import java.util.concurrent.atomic.AtomicReference;
//
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@Ignore
//@CucumberContextConfiguration
//@ContextConfiguration(classes = ApplicationTestContext.class)
//public class TopicServiceStepsDef {
//
//    @Autowired
//    private TopicRepository topicRepository;
//
//    @Autowired
//    private TopicService topicService;
//
//    private final AtomicReference<Flux<Topic>> resultsTopics = new AtomicReference<>();
//    private final AtomicReference<Mono<Topic>> resultTopic = new AtomicReference<>();
//
//    private Topic topicRequest = null;
//
//    @Dado("que exista o topico {string} criado")
//    public void que_exista_o_topico_criado(String topic) {
//        var topics = Lists.list(new Topic(1L, topic));
//        doReturn(topics).when(topicRepository).findAll(Mockito.any(Pageable.class));
//    }
//
//    @Quando("o usuario solicitar pelos topicos")
//    public void o_usuario_solicitar_pelos_topicos() {
//        resultsTopics.set(topicService.find(Pageable.unpaged()));
//    }
//
//    @Entao("o topico {string} devera ser apresentado")
//    public void o_topico_devera_ser_apresentado(String name) {
//        assertNotNull(resultsTopics.get());
//        StepVerifier.create(resultsTopics.get())
//                    .expectNextMatches(t-> name.equals(t.getMessage()))
//                    .verifyComplete();
//        verify(topicRepository, times(1)).findAll(Mockito.any(Pageable.class));
//    }
//
//    @Dado("que o solicite a criacao de um topico {string}")
//    public void que_o_solicite_a_criacao_de_um_topico(String name) {
//        topicRequest = new Topic(null, name);
//        doReturn(new Topic(1L, name)).when(topicRepository).save(topicRequest);
//    }
//
//    @Quando("executar a criacao")
//    public void executar_a_criacao() {
//        resultTopic.set(topicService.save(topicRequest));
//    }
//
//    @Entao("o topico {string} devera ser criado")
//    public void o_topico_devera_ser_criado(String name) {
//        assertNotNull(resultTopic.get());
//    }
//
//    @Dado("que exista o topico {string} com o id {string}")
//    public void que_exista_o_topico_com_o_id(String string, String string2) {
//    }
//
//    @Quando("solicicar a remocao")
//    public void solicicar_a_remocao() {
//    }
//
//    @Entao("o topico {string} com o id {string} foi removido")
//    public void o_topico_com_o_id_foi_removido(String string, String string2) {
//    }
//}
