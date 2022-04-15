package application.model;

import application.dao.Identifiable;

public class Client implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private String egn;
    private String address;
    private String phone;

    public Client(int id) {
        this.id = id;
    }

    public Client(String name, String egn, String address, String phone) {
        this.name = name;
        this.egn = egn;
        this.address = address;
        this.phone = phone;
    }

    public Client(int id, String name, String egn, String address, String phone) {
        this.id = id;
        this.name = name;
        this.egn = egn;
        this.address = address;
        this.phone = phone;
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

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", egn='" + egn + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
