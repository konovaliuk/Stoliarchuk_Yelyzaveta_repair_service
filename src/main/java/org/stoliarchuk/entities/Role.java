package org.stoliarchuk.entities;

public class Role {
    private long role_id;
    private String name;

    public long getRole_id() {
        return this.role_id;
    }
    public void setRole_id(long value) {
        this.role_id = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public void print() {
        System.out.println("{ role_id: " + this.role_id + ", name: " + this.name + " }");
    }

    public Role(long Role_id, String Name) {
        this.role_id = Role_id;
        this.name = Name;
    }

    public Role(String Name) {
        this.name = Name;
    }

    public Role() {

    }
}
