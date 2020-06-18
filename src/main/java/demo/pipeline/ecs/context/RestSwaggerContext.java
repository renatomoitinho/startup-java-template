package demo.pipeline.ecs.context;

import demo.pipeline.ecs.context.properties.ProjectProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestSwaggerContext {
    @Bean
    public OpenAPI springShopOpenAPI(ProjectProperties properties) {
        return new OpenAPI()
                .info(new Info()
                        .title(properties.getName())
                        .description(properties.getDescription() +
                                "\n" + " use <strong>'Accept-Language'</strong> to change locale and messages response.")
                        .version("v".concat(properties.getVersion())));
    }
}