package com.example.medpal;

public class User {
    private Integer id;
    private String name;
    private String phone;
    private String password;
    private String logged;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){

    }
    public String getName(){

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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public String getLogged() {
        return logged;
    }

    public void setLogged(String logged) {
        this.logged = logged;
    }
}

