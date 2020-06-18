package demo.pipeline.ecs.rest.controller;

import demo.pipeline.ecs.model.Topic;
import demo.pipeline.ecs.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
    public Map<?, ?> find(@RequestParam("page") int page, @RequestParam("size") int size) {
        var result = topicService.find(PageRequest.of(page, size));
        return Map.of(
                "content", result.getContent(),
                "pageNumber", result.getNumber(),
                "pageSize", result.getSize(),
                "totalPages", result.getTotalPages());
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "create new topic")
    public Topic create(@RequestBody @Valid Topic topic) {
        topic.setId(null);
        return topicService.save(topic);
    }

    @ResponseBody
    @GetMapping(PATH_KEY_ID)
    @Operation(summary = "get single topic")
    public Topic get(@PathVariable("id") Long id) {
        return topicService.getById(id).orElse(new Topic());
    }

    @PutMapping(PATH_KEY_ID)
    @Operation(summary = "update single topic")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid Topic topic) {
        topicService.update(id, topic);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(PATH_KEY_ID)
    @Operation(summary = "delete single topic")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
