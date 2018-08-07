package com.example.android.sharekaro;

class User {
    String contact, pwd, name, gender;

    public User() {
    }

    public User(String contact, String gender, String name, String pwd) {
        this.contact = contact;
        this.pwd = pwd;
        this.name = name;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }
}
