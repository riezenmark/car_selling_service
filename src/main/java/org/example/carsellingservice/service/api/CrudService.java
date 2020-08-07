package org.example.carsellingservice.service.api;

public interface CrudService<T, ID> {
    Iterable<T> getAll();

    Iterable<T> getAllWithReferenceID(ID id);

    T addOne(T t);

    void updateOne(T previousT, T t);

    void deleteOne(T t);
}
