package application.model;

import application.dao.Identifiable;

public class Position implements Identifiable<Integer> {
    private Integer id;
    private String name;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public Position(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Позиция ID: " + id + "\n"+
                "Име: " + name + "\n";
    }
}
