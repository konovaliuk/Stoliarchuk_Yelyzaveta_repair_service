package org.stoliarchuk.entities;

public class Role {
    private long roleId;
    private String name;

    public long getRoleId() {
        return this.roleId;
    }
    public void setRoleId(long value) {
        this.roleId = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public void print() {
        System.out.println("{ role_id: " + this.roleId + ", name: " + this.name + " }");
    }

    public Role(long RoleId, String Name) {
        this.roleId = RoleId;
        this.name = Name;
    }

    public Role(String Name) {
        this.name = Name;
    }

    public Role() {

    }
}
