package demo.pipeline.ecs.service.job;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.distribution.HistogramGauges;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class TopicMockService {

    private static final String JAVA = "java";
    private static final String PYTHON = "python";
    private static final String JAVASCRIPT = "javascript";
    private static final String GO = "go";
    private static final String[] languages = {JAVA, PYTHON, JAVASCRIPT, GO};

    private final MeterRegistry registry;
    private final DistributionSummary summary;

    public TopicMockService(MeterRegistry registry) {
        this.registry = registry;
        this.summary = DistributionSummary.builder("topic.python.age")
                //.publishPercentiles(.50) // median and 95th percentile
                .publishPercentileHistogram()
                //.sla(100L)
                .minimumExpectedValue(18L)
                .maximumExpectedValue(50L)
                //.sla()
                .register(this.registry);

    }

    public void execution() {
        ageAverage("lang");

//        Stream.of(languages).forEach(lang -> {
//            registerLikes(lang);
//            //ageAverage(lang);
//            var timeKey = String.format("topic.%s.execution.time", lang);
//            registry.timer(timeKey).record(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(range(200, 600));
//                } catch (InterruptedException ignore) {
//                }
//            });
//        });
    }

    private void ageAverage(String lang) {
       this.summary.record(range(18, 50));
    }

    private void registerLikes(String lang) {
        var likeKey = String.format("topic.%s.like", lang);
        var unlikeKey = String.format("topic.%s.unlike", lang);
        var random = range(0, 10) % 2 == 0;

        if (random) {
            registry.counter(likeKey).increment();
            return;
        }

        registry.counter(unlikeKey).increment();
    }

    private int range(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
