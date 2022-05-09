package application.model;

import application.dao.Identifiable;

public class CurrencyType implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private String shortName;

    public CurrencyType() {
    }

    public CurrencyType(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public CurrencyType(Integer id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Валута ID: " + id + "\n"+
                "Име: " + name+ "\n"+
                "Кратко име: " + shortName + "\n";
    }
}
