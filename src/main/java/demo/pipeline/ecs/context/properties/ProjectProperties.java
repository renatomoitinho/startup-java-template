package demo.pipeline.ecs.context.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties("project.info")
public class ProjectProperties {

    private final String groupId;
    private final String artifactId;
    private final String name;
    private final String version;
    private final String description;
    private final String documentation;

    public ProjectProperties(String groupId, String artifactId, String name, String version, String description, String documentation) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.name = name;
        this.version = version;
        this.description = description;
        this.documentation = documentation;
    }
}
