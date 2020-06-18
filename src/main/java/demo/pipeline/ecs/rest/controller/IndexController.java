package demo.pipeline.ecs.rest.controller;

import demo.pipeline.ecs.context.properties.ProjectProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@SuppressWarnings("unused")
@Tag(name = "Index Service", description = "Basic Info api")
public class IndexController {

    private final Map<String, String> info;
    private final ProjectProperties properties;

    @Autowired
    public IndexController(final ProjectProperties properties) {
        this.properties = properties;
        this.info = Map.of(
                "Name", properties.getName(),
                "Version", properties.getVersion(),
                "Description", properties.getDescription(),
                "Swagger", properties.getDocumentation()
        );
    }

    @GetMapping
    @Operation(summary = "Welcome")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(info);
    }

    @GetMapping("/info")
    @Operation(summary = "Project info")
    public ResponseEntity<?> info() {
        return ResponseEntity.ok(this.properties);
    }
}
