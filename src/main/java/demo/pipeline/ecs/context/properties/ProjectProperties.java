package demo.pipeline.ecs.context.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("project.info")
public class ProjectProperties {
    private final String groupId;
    private final String artifactId;
    private final String name;
    private final String version;
    private final String description;
    private final String documentation;
}
