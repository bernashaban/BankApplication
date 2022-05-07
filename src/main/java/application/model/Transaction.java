package application.model;

import application.dao.Identifiable;

import java.sql.Timestamp;

public class Transaction implements Identifiable<Integer> ,Comparable<Transaction>{
    private Integer id;
    private TransactionType type;
    private Employee employee;
    private Account account;
    private double amount;
    private Timestamp transactionDate;

    public Transaction() {
    }

    public Transaction(Integer id, TransactionType type, Employee employee, Account account, double amount, Timestamp transactionDate) {
        this.id = id;
        this.type = type;
        this.employee = employee;
        this.account = account;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction(TransactionType type, Employee employee, Account account, double amount, Timestamp transactionDate) {
        this.type = type;
        this.employee = employee;
        this.account = account;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", employee=" + employee +
                ", account=" + account +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }

    @Override
    public int compareTo(Transaction other) {
       return this.getTransactionDate().compareTo(other.getTransactionDate());
    }
}
