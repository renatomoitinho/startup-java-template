package demo.pipeline.ecs.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ErrorInfo.ErrorInfoBuilder.class)
@Getter
@ToString
@Builder(builderClassName = "ErrorInfoBuilder", toBuilder = true)
public class ErrorInfo {
    private Integer status;
    private String message;
    private String error;
    private Map<String, String> errors;
    private String path;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ErrorInfoBuilder {
    }
}
