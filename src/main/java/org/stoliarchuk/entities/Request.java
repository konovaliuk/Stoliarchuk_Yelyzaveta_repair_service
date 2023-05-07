package org.stoliarchuk.entities;

import java.sql.Date;

public class Request {
    private long requestId;
    private long userId;
    private long statusId;
    private String requestDescription;
    private String productName;
    private String productModel;
    private Date createdAt;
    private int repairCost;
    private String declinationReason;

    public long getRequestId() {
        return this.requestId;
    }
    public void setRequestId(long value) {
        this.requestId = value;
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long value) {
        this.userId = value;
    }
    public long getStatusId() {
        return this.statusId;
    }
    public void setStatusId(long value) {
        this.statusId = value;
    }
    public String getRequestDescription() {
        return this.requestDescription;
    }
    public void setRequestDescription(String value) {
        this.requestDescription = value;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String value) {
        this.productName = value;
    }
    public String getProductModel() {
        return this.productModel;
    }
    public void setProductModel(String value) {
        this.productModel = value;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date value) {
        this.createdAt = value;
    }
    public int getRepairCost() {
        return this.repairCost;
    }
    public void setRepairCost(int value) {
        this.repairCost = value;
    }
    public void setDeclinationReason(String value) {
        this.declinationReason = value;
    }
    public String getDeclinationReason() {
        return this.declinationReason;
    }

    public void print() {
        System.out.println("{ request_id: " + this.requestId + ", user_id: " + this.userId + ", status_id: " + this.statusId + ", request_description: " + this.requestDescription + ", product_name: " + this.productName + ", product_model:" + this.productModel + ", created_at: " + this.createdAt + ", repair_cost: " + this.repairCost + ", declination_reason: " +  this.declinationReason + " }");
    }

    public Request(long RequestId, long UserId, long StatusId, String RequestDescription, String ProductName, String ProductModel, Date CreatedAt, int RepairCost, String DeclinationReason) {
        this.requestId = RequestId;
        this.userId = UserId;
        this.statusId = StatusId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
        this.createdAt = CreatedAt;
        this.repairCost = RepairCost;
        this.declinationReason = DeclinationReason;
    }

    public Request(long UserId, long StatusId, String RequestDescription, String ProductName, String ProductModel, int RepairCost, String Declination_reason) {
        this.userId = UserId;
        this.statusId = StatusId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
        this.repairCost = RepairCost;
        this.declinationReason = Declination_reason;
    }

    public Request(long UserId, long StatusId, String RequestDescription, String ProductName, String ProductModel, int RepairCost) {
        this.userId = UserId;
        this.statusId = StatusId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
        this.repairCost = RepairCost;
    }

    public Request(long UserId, long StatusId, String RequestDescription, String ProductName, String ProductModel, String DeclinationReason) {
        this.userId = UserId;
        this.statusId = StatusId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
        this.declinationReason = DeclinationReason;
    }

    public Request(long UserId, long StatusId, String RequestDescription, String ProductName, String ProductModel) {
        this.userId = UserId;
        this.statusId = StatusId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
    }

    public Request(long UserId, String RequestDescription, String ProductName, String ProductModel) {
        this.userId = UserId;
        this.requestDescription = RequestDescription;
        this.productName = ProductName;
        this.productModel = ProductModel;
    }

    public Request() {

    }
}