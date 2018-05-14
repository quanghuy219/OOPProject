package model.cashflow;

import java.time.LocalDate;

public class Entry {
   
    private LocalDate date;
    private double revenue;     // revenue from sales figures
    private double inventory;   // total goods receipt
    private double expense;     // other operation costs
    private int no0fProductsSold;
    private int no0fProductsBought;


    public Entry(LocalDate _date, double _revenue, int _no0fProductsSold, double _inventory,int _no0fProductsBought, double _expense) {
        this.setDate(_date);
        this.setRevenue(_revenue);
        this.setNo0fProductsSold(_no0fProductsSold);

        this.setInventory(_inventory);
        this.setNo0fProductsBought(_no0fProductsBought);

        this.setExpense(_expense);



    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public int getNo0fProductsSold() {
        return no0fProductsSold;
    }

    public void setNo0fProductsSold(int no0fProductsSold) {
        this.no0fProductsSold = no0fProductsSold;
    }

    public int getNo0fProductsBought() {
        return no0fProductsBought;
    }

    public void setNo0fProductsBought(int no0fProductsBought) {
        this.no0fProductsBought = no0fProductsBought;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }
}
