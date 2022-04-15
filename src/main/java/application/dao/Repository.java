package application.dao;

import application.exception.AlreadyExistingEntityException;
import application.exception.NonExistingEntityException;

import java.util.Collection;

public interface Repository<K, V extends Identifiable<K>> {
    Collection<V> findAll();

    V findById(K id);

    V create(V entity) throws AlreadyExistingEntityException;

    V update(V entity) throws NonExistingEntityException;

    V deleteById(K id) throws NonExistingEntityException;
}
