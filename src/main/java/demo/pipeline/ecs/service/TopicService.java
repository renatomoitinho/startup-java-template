package demo.pipeline.ecs.service;

import demo.pipeline.ecs.model.Topic;
import demo.pipeline.ecs.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class TopicService {

    private final TopicRepository repository;
    private final TransactionTemplate transactionTemplate;
    private final Scheduler scheduler;

    public TopicService(TopicRepository repository, TransactionTemplate transactionTemplate,
                        @Qualifier("jdbcSchedule") Scheduler scheduler) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
        this.scheduler = scheduler;
    }

    public Flux<Topic> find() {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll())).subscribeOn(scheduler);
    }

    public Mono<Topic> getById(Long id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Mono<Topic> save(final Topic topic) {
        return Mono.fromCallable(() -> transactionTemplate.execute(_a -> repository.save(topic)));
    }

    public Mono<Topic> update(Long id, Topic topic) {
        return getById(id).flatMap(t -> {
            t.setMessage(topic.getMessage());
            return save(t);
        }).defaultIfEmpty(new Topic());
    }

    public Mono<?> delete(final Long id) {
        return Mono.fromCallable(() -> transactionTemplate.execute(_a -> {
            repository.deleteById(id);
            return true;
        })).subscribeOn(scheduler);
    }
}
