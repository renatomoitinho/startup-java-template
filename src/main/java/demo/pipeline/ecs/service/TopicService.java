package demo.pipeline.ecs.service;

import demo.pipeline.ecs.model.Topic;
import demo.pipeline.ecs.repository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    private final Logger LOGGER = LoggerFactory.getLogger(TopicService.class);

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Page<Topic> find(final Pageable pageable) {
        LOGGER.info("find topics call page={} size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable);
    }

    public Optional<Topic> getById(Long id) {
        LOGGER.info("getById topic call id={}", id);
        Optional<Topic> opt = repository.findById(id);
        if (opt.isEmpty()) {
            LOGGER.warn("getById not found topic id={}", id);
        }
        return opt;
    }

    public Topic save(final Topic topic) {
        var topicSaved = repository.save(topic);
        LOGGER.info("save topic was successfully inserted {}", topicSaved);
        return topicSaved;
    }

    public void update(Long id, Topic topic) {
        getById(id).map(tp -> update(tp, topic)).ifPresent(tp -> {
            LOGGER.info("update topic was updated successfully {}", tp);
        });
    }

    public void delete(final Long id) {
        try {
            repository.deleteById(id);
            LOGGER.info("delete topic was successfully removed {}", id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("delete Topic not exists to id={} message={}", id, e.getMessage());
        }
    }

    private Topic update(Topic source, Topic target) {
        source.setMessage(target.getMessage());
        return repository.save(source);
    }
}
