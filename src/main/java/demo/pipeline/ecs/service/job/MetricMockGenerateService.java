package demo.pipeline.ecs.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MetricMockGenerateService {

    private final Logger logger = LoggerFactory.getLogger(MetricMockGenerateService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final TopicMockService mockService;

    public MetricMockGenerateService(TopicMockService mockService) {
        this.mockService = mockService;
    }

    @Scheduled(fixedRate = 10_000)
    public void generate() {
        logger.info("The time is now {}", dateFormat.format(new Date()));
        this.mockService.execution();
    }
}
