package application.dao.impl;

import application.dao.Identifiable;
import application.dao.Repository;
import application.exception.AlreadyExistingEntityException;
import application.exception.NonExistingEntityException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepository<K, V extends Identifiable<K>> implements Repository<K, V> {

    private Map<K, V> entities = new HashMap<>();

    public AbstractRepository() {
    }


    @Override
    public Collection<V> findAll() {
        return entities.values();
    }

    @Override
    public V findById(K id) {
        return entities.get(id);
    }

    @Override
    public V create(V entity) throws AlreadyExistingEntityException {
        if(entities.containsKey(entity.getId())){
            throw new AlreadyExistingEntityException("Error: The entity you want to add already exists!");
        }
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V update(V entity) throws NonExistingEntityException {
        V old = findById(entity.getId());
        if(old == null){
            throw new NonExistingEntityException("Error: Entity with ID="+entity.getId()+"does not exist.");
        }
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V deleteById(K id) throws NonExistingEntityException {
        V old = entities.remove(id);
        if(old == null){
            throw new NonExistingEntityException("Error: Entity with ID="+id+"does not exist.");
        }
        return old;
    }
}
