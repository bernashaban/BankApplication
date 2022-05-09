package application.model;

import application.dao.Identifiable;

public class Employee implements Identifiable<Integer> {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Position position;

    public Employee(Integer id, String username, String password, String name, String phone, Position position) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

    public Employee(String username, String password, String name, String phone, Position position) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

    public Employee() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Служител ID: " + id + "\n"+
                "Име: " + name + "\n"+
                "Телефон: " + phone + "\n"+
                "Позиция: " + position.getName()+ "\n";
    }
}
