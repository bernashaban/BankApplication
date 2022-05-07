package application.service;

import application.dao.impl.CurrencyRepository;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;

import java.util.Collection;

public class CurrencyService implements Service<CurrencyType> {
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Collection<CurrencyType> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public CurrencyType getById(Integer id) {
        return currencyRepository.findById(id);
    }

    @Override
    public CurrencyType add(CurrencyType entity) {
        return currencyRepository.create(entity);
    }

    @Override
    public CurrencyType update(CurrencyType entity) {
        return currencyRepository.update(entity);
    }

    @Override
    public CurrencyType delete(Integer id) {
        return currencyRepository.deleteById(id);
    }

    public CurrencyType getCurrencyByShortName(String shortName) {
        return currencyRepository.findCurrencyByShortName(shortName);
    }
}
