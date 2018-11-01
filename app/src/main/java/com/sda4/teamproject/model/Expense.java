package com.sda4.teamproject.model;

import java.util.Date;

public class Expense {

    private int id;

    private double amount;
    private String category;
    private String currency;
    private Date datetime;
    private String remarks;
    private User user;

    public Expense() {

    }

    public Expense(int id, double amount, String category, String currency, Date datetime, String remarks, User user) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.datetime = datetime;
        this.remarks = remarks;
        this.user = user;
    }

    public Expense(double amount, String category, String currency, Date datetime, String remarks, User user) {
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.datetime = datetime;
        this.remarks = remarks;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
