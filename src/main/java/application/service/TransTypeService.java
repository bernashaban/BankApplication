package application.service;

import application.dao.impl.TransTypeRepository;
import application.model.TransactionType;

import java.util.Collection;

public class TransTypeService implements Service<TransactionType> {
    private final TransTypeRepository transTypeRepository;

    public TransTypeService(TransTypeRepository transTypeRepository) {
        this.transTypeRepository = transTypeRepository;
    }

    @Override
    public Collection<TransactionType> getAll() {
        return transTypeRepository.findAll();
    }

    @Override
    public TransactionType getById(Integer id) {
        return transTypeRepository.findById(id);
    }

    @Override
    public TransactionType add(TransactionType entity) {
        return transTypeRepository.create(entity);
    }

    @Override
    public TransactionType update(TransactionType entity) {
        return transTypeRepository.update(entity);
    }

    @Override
    public TransactionType delete(Integer id) {
        return transTypeRepository.deleteById(id);
    }
}
