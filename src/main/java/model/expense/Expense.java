package model.expense;

import controller.App;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Expense {
    private String expenseID;
    private Double cost;
    private LocalDate purchaseDate;
    private String purchaser;
    private String remark;

    public Expense()
    {
        setExpenseID("");
        setCost(0.0);
        setPurchaseDate(null);
        setPurchaser("");
        setRemark("");
    }

    public Expense(String _expenseID, Double _cost, LocalDate _date, String _purchaser, String _remark)
    {
        this.setExpenseID(_expenseID);
        this.setCost(_cost);
        this.setPurchaseDate(_date);
        this.setPurchaser(_purchaser);
        this.setRemark(_remark);
    }

    public String getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    /*
    *   Format data for writing in file
     */
    public String toString()
    {
        String text = "";
        text = text.concat(getExpenseID()).concat("|");
        text = text.concat(String.valueOf(getCost())).concat("|");
        text = text.concat(getPurchaseDate().toString()).concat("|");
        text = text.concat(getPurchaser()).concat("|");
        text = text.concat(App.stringToAscii(getRemark())).concat(" |");

        return text;
    }

    /*
    *   Destructure a line to expected data when reading file
     */
    public static Expense valueOf(String text)
    {
        String[] parts = text.split(Pattern.quote("|"));

//        for(String p: parts)
        {
            System.out.print("\""+parts.length+"\""+" | ");
        }
        System.out.println();

        Expense e = new Expense();
        e.setExpenseID(parts[0]);
        e.setCost(Double.valueOf(parts[1]));
        e.setPurchaseDate(LocalDate.parse(parts[2]));
        e.setPurchaser(parts[3]);
        e.setRemark(App.asciiToString(parts[4]));

        return e;
    }
}
