package com.example.identity;

public class UserModel {
    private  String  name,profession,email,age,country ;
    public UserModel() { }
    public UserModel(String name , String profession , String email , String age , String country)
    {
        this.name = name ;
        this.profession = profession ;
        this.email = email ;
        this.age = age ;
        this.country = country ;
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
    public String getAge() {return age;}
    public void setAge(String age) {this.age = age;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
}
