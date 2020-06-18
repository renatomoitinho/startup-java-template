package demo.pipeline.ecs.rest.errors;

import demo.pipeline.ecs.util.Safely;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler implements Safely {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(HttpServletRequest req, MethodArgumentNotValidException e) {

        Map<String, String> errors = e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, f -> safe(f.getDefaultMessage())));

        var status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErrorInfo errorInfo = ErrorInfo.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .path(req.getRequestURI())
                .errors(errors)
                .build();

        LOGGER.warn("Error Handler {} {}", LocaleContextHolder.getLocale(), errorInfo);
        return ResponseEntity.unprocessableEntity().body(errorInfo);
    }
}
