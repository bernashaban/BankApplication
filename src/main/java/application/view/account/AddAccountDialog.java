package application.view.account;

import application.exception.NonExistingEntityException;
import application.model.Account;
import application.model.Client;
import application.model.CurrencyType;
import application.service.ClientService;
import application.service.CurrencyService;
import application.view.EntityDialog;

import java.util.Scanner;


public class AddAccountDialog implements EntityDialog<Account> {
    public static Scanner scanner = new Scanner(System.in);
    private ClientService clientService;
    private CurrencyService currencyService;

    public AddAccountDialog(ClientService clientService, CurrencyService currencyService) {
        this.clientService = clientService;
        this.currencyService = currencyService;
    }

    @Override
    public Account input() throws NonExistingEntityException {
        var account = new Account();

        while(account.getAccountNum()==0){
            System.out.println("Номер на сметка: ");
            var answer = scanner.nextLine();
            int accNum = 0;
            try{
                accNum = Integer.parseInt(answer);
            }catch (NumberFormatException e ){
                System.out.println("Невалиден номер! Моля въведете само числа.");
            }
            account.setAccountNum(accNum);
        }
        while(account.getInterest()==0){
            System.out.println("Лихва: ");
            var answer = scanner.nextLine();
            double interest = 0;
            try{
                interest = Double.parseDouble(answer);
            }catch (NumberFormatException e ){
                System.out.println("Невалиден формат на лихва! \nМоля въведете число разделено със точка.(Пример: 0.1))");
            }
            account.setInterest(interest);
        }
        while(account.getBalance()==0){
            System.out.println("Баланс: ");
            var answer = scanner.nextLine();
            double balance = 0;
            try{
                balance = Double.parseDouble(answer);
            }catch (NumberFormatException e ){
                System.out.println("Невалиден формат на баланс! \nМоля въведете число разделено със точка.(Пример: 100.90))");
            }
            account.setInterest(balance);
        }
        while (account.getClient() == null) {
            clientService.getAll().forEach(System.out::println);
            System.out.println("ID на клиент: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            Client client =  clientService.getById(id);
            if(client==null){
                System.out.println("Клиент с такова ID не съществува!");
            }
            account.setClient(client);
        }
        while (account.getCurrencyType() == null) {
            currencyService.getAll().forEach(System.out::println);
            System.out.println("ID на валута: ");
            var answer = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                System.out.println("Невалиден формат на ID. Моля въведете число.");
            }
            CurrencyType currencyType =  currencyService.getById(id);
            if(currencyType==null){
                System.out.println("Валута с такова ID не съществува!");
            }
            account.setCurrencyType(currencyType);
        }

      return account;
    }
}
