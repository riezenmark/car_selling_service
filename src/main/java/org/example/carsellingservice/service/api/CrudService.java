package org.example.carsellingservice.service.api;

public interface CrudService<T, ID> {
    Iterable<T> getAll();

    Iterable<T> getAllWithReferenceID(ID id);

    T addOne(T t);

    T updateOne(T t);

    T deleteOne(T t);
}
