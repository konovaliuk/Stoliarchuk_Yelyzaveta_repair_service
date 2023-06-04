package org.stoliarchuk.entities;

import java.sql.Date;

public class Request {
    private long request_id;
    private long user_id;
    private long status_id;
    private String request_description;
    private String product_name;
    private String product_model;
    private Date created_at;
    private int repair_cost;
    private String declination_reason;

    public long getRequest_id() {
        return this.request_id;
    }
    public void setRequest_id(long value) {
        this.request_id = value;
    }
    public long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(long value) {
        this.user_id = value;
    }
    public long getStatus_id() {
        return this.status_id;
    }
    public void setStatus_id(long value) {
        this.status_id = value;
    }
    public String getRequest_description() {
        return this.request_description;
    }
    public void setRequest_description(String value) {
        this.request_description = value;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setProduct_name(String value) {
        this.product_name = value;
    }
    public String getProduct_model() {
        return this.product_model;
    }
    public void setProduct_model(String value) {
        this.product_model = value;
    }
    public Date getCreated_at() {
        return this.created_at;
    }
    public void setCreated_at(Date value) {
        this.created_at = value;
    }
    public int getRepair_cost() {
        return this.repair_cost;
    }
    public void setRepair_cost(int value) {
        this.repair_cost = value;
    }
    public void setDeclination_reason(String value) {
        this.declination_reason = value;
    }
    public String getDeclination_reason() {
        return this.declination_reason;
    }

    public void print() {
        System.out.println("{ request_id: " + this.request_id + ", user_id: " + this.user_id + ", status_id: " + this.status_id + ", request_description: " + this.request_description + ", product_name: " + this.product_name + ", product_model:" + this.product_model + ", created_at: " + this.created_at + ", repair_cost: " + this.repair_cost + ", declination_reason: " +  this.declination_reason + " }");
    }

    public Request(long Request_id, long User_id, long Status_id, String Request_description, String Product_name, String Product_model, Date Created_at, int Repair_cost, String Declination_reason) {
        this.request_id = Request_id;
        this.user_id = User_id;
        this.status_id = Status_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
        this.created_at = Created_at;
        this.repair_cost = Repair_cost;
        this.declination_reason = Declination_reason;
    }

    public Request(long User_id, long Status_id, String Request_description, String Product_name, String Product_model, int Repair_cost, String Declination_reason) {
        this.user_id = User_id;
        this.status_id = Status_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
        this.repair_cost = Repair_cost;
        this.declination_reason = Declination_reason;
    }

    public Request(long User_id, long Status_id, String Request_description, String Product_name, String Product_model, int Repair_cost) {
        this.user_id = User_id;
        this.status_id = Status_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
        this.repair_cost = Repair_cost;
    }

    public Request(long User_id, long Status_id, String Request_description, String Product_name, String Product_model, String Declination_reason) {
        this.user_id = User_id;
        this.status_id = Status_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
        this.declination_reason = Declination_reason;
    }

    public Request(long User_id, long Status_id, String Request_description, String Product_name, String Product_model) {
        this.user_id = User_id;
        this.status_id = Status_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
    }

    public Request(long User_id, String Request_description, String Product_name, String Product_model) {
        this.user_id = User_id;
        this.request_description = Request_description;
        this.product_name = Product_name;
        this.product_model = Product_model;
    }

    public Request() {

    }
}