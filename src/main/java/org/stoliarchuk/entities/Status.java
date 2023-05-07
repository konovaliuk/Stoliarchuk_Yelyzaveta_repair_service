package org.stoliarchuk.entities;

public class Status {
    private long statusId;
    private String name;

    public long getStatusId() {
        return this.statusId;
    }
    public void setStatus_id(long value) {
        this.statusId = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public void print() {
        System.out.println("{ status_id: " + this.statusId + ", name: " + this.name + " }");
    }

    public Status(long Status_id, String Name) {
        this.statusId = Status_id;
        this.name = Name;
    }

    public Status(String Name) {
        this.name = Name;
    }

    public Status() {

    }
}