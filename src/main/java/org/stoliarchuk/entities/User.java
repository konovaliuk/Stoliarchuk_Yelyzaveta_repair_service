package org.stoliarchuk.entities;

public class User {
    private long user_id;
    private long role_id;
    private String email;
    private String username;
    private String password;

    public long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(long value) {
        this.user_id = value;
    }
    public long getRole_id() {
        return this.role_id;
    }
    public void setRole_id(long value) {
        this.role_id = value;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String value) {
        this.email = value;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String value) {
        this.username = value;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String value) {
        this.password = value;
    }

    public void print() {
        System.out.println("{ user_id: " + this.user_id + ", role: " + this.role_id + ", email: " + this.email + ", username: " + this.username + ", password: " + this.password + " }");
    }

    public User(long User_id, long Role_id, String Email, String Username, String Password) {
        this.user_id = User_id;
        this.role_id = Role_id;
        this.email = Email;
        this.username = Username;
        this.password = Password;
    }

    public User(long Role_id, String Email, String Username, String Password) {
        this.role_id = Role_id;
        this.email = Email;
        this.username = Username;
        this.password = Password;
    }

    public User(String Email, String Username, String Password) {
        this.email = Email;
        this.username = Username;
        this.password = Password;
    }

    public User() {

    }
}

