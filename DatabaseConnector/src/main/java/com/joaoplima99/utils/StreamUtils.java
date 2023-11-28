package com.joaoplima99.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.collect.Lists.newArrayList;

public final class StreamUtils<T> {

    //This class should not be instantiated.
    @Deprecated(since = "1.0")
    private StreamUtils() {}

    public static void requireNonNullElements(List<?> list) {
        Objects.requireNonNull(list);
        list.forEach(Objects::requireNonNull);
    }

    public static <T> List<T> mergeLists(List<T>... lists) {
        Objects.requireNonNull(lists);
        Arrays.stream(lists).forEach(StreamUtils::requireNonNullElements);
        AtomicInteger initialCapacity = new AtomicInteger(0);

        Arrays.stream(lists).forEach(list -> initialCapacity.addAndGet(list.size()));

        List<T> rList = new ArrayList<>(initialCapacity.get());
        Arrays.stream(lists).forEach(rList::addAll);

        return rList;
    }

    public static void main(String[] args) {
    }
}
