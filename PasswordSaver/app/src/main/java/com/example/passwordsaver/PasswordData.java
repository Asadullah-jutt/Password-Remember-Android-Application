package com.example.passwordsaver;
public class PasswordData {
    int id;
    String username;
    String password;
    String URL;

    String ofUser;
    public PasswordData() {
    }

    public PasswordData( int id ,String ofUser ,String name, String text , String URL) {

        this.id = id ;
        this.ofUser = ofUser ;
        this.username = name;
        this.password = text;
        this.URL = URL ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOfUser() {
        return ofUser;
    }
    public void setofUser(String ofUser) {
        this.ofUser = ofUser;
    }
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String note) {
        this.password = note;
    }
}
