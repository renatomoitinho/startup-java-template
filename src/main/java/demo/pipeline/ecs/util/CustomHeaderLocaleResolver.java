package demo.pipeline.ecs.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class CustomHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    public static final Locale PT_BR = Locale.forLanguageTag("pt-BR");
    private static final String ACCEPT_REGEX = "^([a-z]{2}-[A-Z]{2}),?.*$";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        var language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

        if(StringUtils.isNotEmpty(language) && language.matches(ACCEPT_REGEX)) {
            language = language.replaceAll(ACCEPT_REGEX, "$1");
            return Locale.forLanguageTag(language);
        }

        return PT_BR;
    }
}
