package application.view.clientMenuDialogs;

import application.exception.NonExistingEntityException;
import application.model.Client;
import application.model.Transaction;
import application.service.TransactionService;

import java.util.*;


public class SearchAllTransactions {

    private Client client;
    private TransactionService transactionService;

    public SearchAllTransactions(Client client, TransactionService transactionService) {
        this.client = client;
        this.transactionService = transactionService;
    }

    public Collection<Transaction> input() throws NonExistingEntityException {
        List<Transaction> clientsTransactions = transactionService.getAllTransactionByClient(client);
        Collections.sort(clientsTransactions);
        return clientsTransactions;
    }
}
