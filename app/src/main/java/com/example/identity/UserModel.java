package com.example.identity;

public class UserModel {
    private  String  name,profession,email;

    public UserModel() {
    }

    public UserModel(String name , String profession , String email)
    {
        this.name = name ;
        this.profession = profession ;
        this.email = email ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
