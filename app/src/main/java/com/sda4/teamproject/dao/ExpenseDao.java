package com.sda4.teamproject.dao;

import com.sda4.teamproject.model.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseDao {
    public void add(Expense expense);
    public void delete(int expense_id);
    public List<Expense> getExpenseList(String condition);
    public void getSingleExpense(int expense_id);
    public void updateExpense(int expense_id, double amount, String category, String currency, Date datetime,String remarks);
}
