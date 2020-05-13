//package demo.pipeline.ecs.context;
//
//import com.google.common.base.Predicates;
//import com.google.common.collect.Sets;
//import demo.pipeline.ecs.context.properties.ProjectProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.time.LocalTime;
//
//@Configuration
//@EnableS
//public class SwaggerContext {
//
//    @Bean
//    public Docket moduleApi(ProjectProperties properties) {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .consumes(Sets.newHashSet("application/json"))
//                .produces(Sets.newHashSet("application/json"))
//                .select()
//                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
//                .paths(PathSelectors.any())
//                .build()
//                .directModelSubstitute(LocalTime.class, String.class)
//                .apiInfo(medata(properties));
//    }
//
//    private ApiInfo medata(ProjectProperties properties) {
//        return new ApiInfoBuilder()
//                .title(properties.getName())
//                .version(properties.getVersion())
//                .description(properties.getDescription())
//                .build();
//    }
//}
