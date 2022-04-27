package application.model;

import application.dao.Identifiable;

public class Client  implements Identifiable<Integer> {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String egn;
    private String address;
    private String phone;

    public Client(Integer id, String username, String password, String name, String egn, String address, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.egn = egn;
        this.address = address;
        this.phone = phone;
    }

    public Client(String username, String password, String name, String egn, String address, String phone) {
        this.username = username;
        this.password = password;
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
