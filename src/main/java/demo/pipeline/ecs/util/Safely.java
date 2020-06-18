package demo.pipeline.ecs.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

public interface Safely {

    default <T> Set<T> safe(Set<T> sets) {
        if (isNull(sets)) {
            return new HashSet<>();
        }
        return sets;
    }

    default <T> List<T> safe(List<T> list) {
        if (isNull(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    default String safe(String obj) {
        if (obj == null)
            return StringUtils.EMPTY;
        return obj;
    }

}
