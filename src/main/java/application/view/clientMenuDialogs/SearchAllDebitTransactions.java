package application.view.clientMenuDialogs;

import application.model.Client;
import application.model.Transaction;
import application.service.TransactionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchAllDebitTransactions {
    private Client client;
    private TransactionService transactionService;

    public SearchAllDebitTransactions(Client client, TransactionService transactionService) {
        this.client = client;
        this.transactionService = transactionService;
    }

    public Collection<Transaction> input() {
        List<Transaction> clientsTransactions = transactionService.getAllTransactionByClient(client);
        List<Transaction> debitTransactions = new ArrayList<>();

        for (Transaction transaction : clientsTransactions) {
            if(transaction.getType().getId()==1){
                //Debit type id  == 1
                debitTransactions.add(transaction);
            }
        }
        return debitTransactions;
    }
}
