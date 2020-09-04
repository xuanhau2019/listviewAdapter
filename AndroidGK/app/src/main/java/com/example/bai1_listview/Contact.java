package com.example.bai1_listview;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name,email,sdt;

    public Contact(String name, String email, String sdt) {
        this.name = name;
        this.email = email;
        this.sdt = sdt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
