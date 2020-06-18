package demo.pipeline.ecs.context;

import demo.pipeline.ecs.context.properties.ProjectProperties;
import demo.pipeline.ecs.util.NetworkInfo;
import io.github.mweirauch.micrometer.jvm.extras.ProcessMemoryMetrics;
import io.github.mweirauch.micrometer.jvm.extras.ProcessThreadMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsContext {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(ProjectProperties projectProperties, NetworkInfo info) {
        return registry ->
            registry.config()
                    .commonTags("application", projectProperties.getArtifactId())
                    .commonTags("region", "sa-east-1")
                    .commonTags("instance", info.getHostName());
    }

    @Bean
    public MeterBinder processMemoryMetrics() {
        return new ProcessMemoryMetrics();
    }

    @Bean
    public MeterBinder processThreadMetrics() {
        return new ProcessThreadMetrics();
    }
}
