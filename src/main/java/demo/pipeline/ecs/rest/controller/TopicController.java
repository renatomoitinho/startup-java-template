package demo.pipeline.ecs.rest.controller;

import demo.pipeline.ecs.model.Topic;
import demo.pipeline.ecs.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("topics")
@SuppressWarnings("unused")
@Tag(name = "Topic Service", description = "Topics api operations")
public class TopicController {

    private static final String PATH_KEY_ID = "/{id:[0-9]+}";
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "get topics")
    public Flux<Topic> find() {
        return topicService.find();
    }

    @PostMapping
    @Operation(summary = "create new topic")
    public Mono<Topic> create(@RequestBody @Valid Topic topic) {
        topic.setId(null);
        return topicService.save(topic);
    }

    @ResponseBody
    @GetMapping(PATH_KEY_ID)
    @Operation(summary = "get single topic")
    public Mono<Topic> get(@PathVariable("id") Long id) {
        return topicService.getById(id);
    }

    @PutMapping(PATH_KEY_ID)
    @Operation(summary = "update single topic")
    public Mono<ResponseEntity<Void>> update(@PathVariable("id") Long id, @RequestBody @Valid Topic topic) {
        return topicService.update(id, topic).map((r) -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(PATH_KEY_ID)
    @Operation(summary = "delete single topic")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return topicService.delete(id).map((r) -> ResponseEntity.noContent().build());
    }
}
