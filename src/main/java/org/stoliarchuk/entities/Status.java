package org.stoliarchuk.entities;

public class Status {
    private long status_id;
    private String name;

    public long getStatus_id() {
        return this.status_id;
    }
    public void setStatus_id(long value) {
        this.status_id = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public void print() {
        System.out.println("{ status_id: " + this.status_id + ", name: " + this.name + " }");
    }

    public Status(long Status_id, String Name) {
        this.status_id = Status_id;
        this.name = Name;
    }

    public Status(String Name) {
        this.name = Name;
    }

    public Status() {

    }
}