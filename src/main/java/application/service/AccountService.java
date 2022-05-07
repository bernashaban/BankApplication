package application.service;

import application.dao.impl.AccountRepository;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;

import java.util.Collection;

public class AccountService implements Service<Account> {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Collection<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account add(Account entity) {
        return accountRepository.create(entity);
    }

    @Override
    public Account update(Account entity) {
        return accountRepository.update(entity);
    }

    @Override
    public Account delete(Integer id) {
        return accountRepository.deleteById(id);
    }

    public Collection<Account> getClientAccounts(Client client) {
        return accountRepository.findClientAccounts(client);
    }
    public Collection<Account> getClientAccountsByCurrency(Client client, CurrencyType currencyType) {
        return accountRepository.findClientAccountsByCurrency(client, currencyType);
    }
}
