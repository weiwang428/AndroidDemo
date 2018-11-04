package com.sda4.teamproject.model;

import java.util.Date;

public class Expense {

    private int id;

    private double amount;
    private String category;
    private String currency;
    private String account;
    private Date datetime;
    private String remarks;
    private String username;

    public Expense() {

    }

    public Expense(int id, double amount, String category, String currency, String account, Date datetime, String remarks, String username) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.account = account;
        this.datetime = datetime;
        this.remarks = remarks;
        this.username = username;
    }


    public Expense(double amount, String category, String currency, String account, Date datetime, String remarks, String username) {
        this.amount = amount;
        this.category = category;
        this.currency = currency;
        this.account = account;
        this.datetime = datetime;
        this.remarks = remarks;
        this.username = username;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getUser() {
        return this.username;
    }

    public void setUser(String username) {
        this.username = username;
    }


}
