package controller.cashflow;

import model.cashflow.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CashflowManager {

    private ObservableList<Entry> listEntries;

    public CashflowManager() {
        setListEntries(FXCollections.observableArrayList());
    }

    public void setListEntries(ObservableList<Entry> listEntries) {
        this.listEntries = listEntries;
    }

    public ObservableList<Entry> getListEntries() {
        return listEntries;
    }

    private void addEntry(Entry _entry)
    {
        getListEntries().add(_entry);
    }


    // update revenue when new products sold
    public void addRevenue(LocalDate _date, double _revenue, int _no0fProductsSold)
    {
        for(Entry e: getListEntries())
        {
            if(e.getDate().isEqual(_date))
            {
                e.setRevenue(e.getRevenue() + _revenue);
                e.setNo0fProductsSold(e.getNo0fProductsSold() + _no0fProductsSold);
                return;
            }
        }

        // add new Entry for a new day
        addEntry(new Entry(_date, _revenue, _no0fProductsSold, 0, 0, 0));
    }

    // update inventory receipts if the stock imports new products
    public void addInventory(LocalDate _date, double _inventory, int _no0fProductsBought)
    {
        for(Entry e: getListEntries())
        {
            if(e.getDate().isEqual(_date))
            {
                e.setInventory(e.getInventory() + _inventory);
                e.setNo0fProductsBought(e.getNo0fProductsBought() + _no0fProductsBought);
                return;
            }
        }

        addEntry(new Entry(_date, 0, 0, _inventory, _no0fProductsBought, 0));
    }

    // update expenses
    public void addExpense(LocalDate _date, double _expense)
    {
        for(Entry e: getListEntries())
        {
            if(e.getDate().isEqual(_date))
            {
                e.setExpense(e.getExpense() + _expense);
                return;
            }
        }

        addEntry(new Entry(_date, 0, 0, 0, 0, _expense));
    }
}
