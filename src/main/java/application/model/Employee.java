package application.model;

import application.dao.Identifiable;

public class Employee implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private String phone;
    private Position position;

    public Employee() {
    }

    public Employee(String name, String phone, Position position) {
        this.name = name;
        this.phone = phone;
        this.position = position;
    }

    public Employee(Integer id, String name, String phone, Position position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.position = position;
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", position=" + position +
                '}';
    }
}
