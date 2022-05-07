package application.view.clientMenuDialogs;

import application.exception.NonExistingEntityException;
import application.model.Client;
import application.model.Transaction;
import application.service.TransactionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchAllCreditTransactions {
    private Client client;
    private TransactionService transactionService;

    public SearchAllCreditTransactions(Client client, TransactionService transactionService) {
        this.client = client;
        this.transactionService = transactionService;
    }

    public Collection<Transaction> input() throws NonExistingEntityException {
        List<Transaction> clientsTransactions = transactionService.getAllTransactionByClient(client);
        List<Transaction> creditTransactions = new ArrayList<>();

        for (Transaction transaction : clientsTransactions) {
            if(transaction.getType().getId()==2){
                //Credit type id  == 2
                creditTransactions.add(transaction);
            }
        }
        return creditTransactions;
    }
}
