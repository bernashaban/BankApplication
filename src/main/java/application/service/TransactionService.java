package application.service;

import application.dao.impl.TransactionRepository;
import application.model.Client;
import application.model.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionService implements Service<Transaction> {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Collection<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction add(Transaction entity) {
        return transactionRepository.create(entity);
    }

    @Override
    public Transaction update(Transaction entity) {
        return transactionRepository.update(entity);
    }

    @Override
    public Transaction delete(Integer id) {
        return transactionRepository.deleteById(id);
    }

    public List<Transaction> getAllTransactionByClient(Client client){
        return transactionRepository.getAllTransactionByClient(client);
    }
}
