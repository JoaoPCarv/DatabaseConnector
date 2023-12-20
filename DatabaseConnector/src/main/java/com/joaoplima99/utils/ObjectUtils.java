package com.joaoplima99.utils;

import com.google.common.hash.HashCode;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObjectUtils {

    //This class should not be instantiated.
    @Deprecated(since = "1.0")
    private ObjectUtils() {}

    public static void nullifyObject(Object o) {
        if (o != null) o = null;
    }

    public static void requireNonNullObjects(Object... params) {
        Objects.requireNonNull(params);
        Arrays.stream(params).forEach(Objects::requireNonNull);
    }

    public static int getHashCode(String... params) {
        requireNonNullObjects(params);
        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(params).forEach(param -> i.getAndAdd(HashCode.fromBytes(param.getBytes()).hashCode()));
        return i.get();
    }
}
