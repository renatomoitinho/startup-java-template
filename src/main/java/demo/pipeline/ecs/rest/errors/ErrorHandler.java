package demo.pipeline.ecs.rest.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

@Component
public class ErrorHandler<T extends Throwable> extends DefaultErrorAttributes {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errors = super.getErrorAttributes(request, includeStackTrace);
        return checkErrors(errors);
    }

    private Map<String, Object> checkErrors(Map<String, Object> errors) {
        LOGGER.error("Error Handler {}", errors);

        Object objErrors = errors.get("errors");
        if (objErrors == null) {
            return errors;
        }

        if (!(objErrors instanceof Collection)) {
            return errors;
        }

        List<String> messages = ((Collection<?>) objErrors)
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        if (messages.isEmpty()) {
            return errors;
        }

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, Object> resp = new WeakHashMap<>();
        resp.put("timestamp", errors.get("timestamp"));
        resp.put("path", errors.get("path"));
        resp.put("status", status.value());
        resp.put("error", status.getReasonPhrase());
        resp.put("message", messages);

        return resp;
    }

}
