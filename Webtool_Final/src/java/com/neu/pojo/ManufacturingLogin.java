
package com.neu.pojo;


public class ManufacturingLogin {
    
    private long id;
    private String username;
    private String password;

    public ManufacturingLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public ManufacturingLogin() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "Login{" + "username=" + username + ", password=" + password + '}';
    }
}