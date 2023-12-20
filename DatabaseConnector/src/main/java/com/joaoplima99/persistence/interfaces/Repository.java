package com.joaoplima99.persistence.interfaces;

import java.util.List;

public interface Repository<T> {
    void createEntry(T t);
    T readEntry(Class<T> clazz, int id);
    void updateEntry(T t);
    void deleteEntry(T t);
    List<T> selectAll();
}
