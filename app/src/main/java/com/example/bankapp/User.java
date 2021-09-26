package com.example.bankapp;

public class User {
    private String name ;
    private String accountno;
    private String bal ;
    private String pas ;
    private String email;

    public User(){

    }
    public User(String name, String accountno, String bal, String pas, String email) {
        this.name = name;
        this.accountno = accountno;
        this.bal = bal;
        this.pas = pas;
        this.email = email;

    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getBal() {
        return bal;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
