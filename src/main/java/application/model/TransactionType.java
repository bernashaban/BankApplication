package application.model;

import application.dao.Identifiable;

public class TransactionType implements Identifiable<Integer> {
    private Integer id;
    private String typeName;
    private int coefficient;

    public TransactionType() {
    }

    public TransactionType(String typeName, int coefficient) {
        this.typeName = typeName;
        this.coefficient = coefficient;
    }

    public TransactionType(Integer id, String typeName, int coefficient) {
        this.id = id;
        this.typeName = typeName;
        this.coefficient = coefficient;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Тип на тракзакция ID: " + id + "\n"+
                "Име: " + typeName + "\n"+
                "Коефициент" + coefficient + "\n";
    }
}
