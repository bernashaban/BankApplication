package application.model;


import application.dao.Identifiable;

public class Account implements Identifiable<Integer> {
    private Integer id;
    private int accountNum;
    private double interest;
    private double balance;
    private Client client;
    private CurrencyType currencyType;

    public Account() {
    }

    public Account(Integer id, int accountNum, double interest, double balance, Client client, CurrencyType currencyType) {
        this.id = id;
        this.accountNum = accountNum;
        this.interest = interest;
        this.balance = balance;
        this.client = client;
        this.currencyType = currencyType;
    }

    public Account(int accountNum, double interest, double balance, Client client, CurrencyType currencyType) {
        this.accountNum = accountNum;
        this.interest = interest;
        this.balance = balance;
        this.client = client;
        this.currencyType = currencyType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "Сметка ID: " + id + "\n"+
                "Номер: " + accountNum +"\n"+
                "Лихва: " + interest +"\n"+
                "Баланс: " + balance +"\n"+
                "Клиент: " + client.getName() + "\n"+
                "Валута: " + currencyType.getShortName() + "\n";
    }


}
