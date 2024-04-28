package com.example.passwordsaver;

public class Userdata {
    int id;
    String name;
    String password;
    String email;
    public String getEmail() {
        return email;
    }

    public Userdata(int id,String a , String b , String c)
    {
        this.id = id ;
        this.name = a ;
        this.email = b ;
        this.password = c ;
    }

    public void Setid(int id)
    {
        this.id = id ;
    }
    public int Getid()
    {
        return this.id ;
    }
    public void setEmail(String emailid) {
        this.email = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
