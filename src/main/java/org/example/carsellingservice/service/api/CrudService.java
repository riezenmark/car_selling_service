package org.example.carsellingservice.service.api;

public interface CrudService<T> {
    Iterable<T> getAll();

    T addOne(T t);

    void updateOne(T previousT, T t);

    void deleteOne(T t);
}
