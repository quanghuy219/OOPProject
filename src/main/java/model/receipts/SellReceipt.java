package model.receipts;

import controller.App;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SellReceipt extends Receipt {

//    private String receiptID;
//    private Enum<Category> category;
//    private double totalCost;
//    private LocalDate date;
//    private String remark;
//
//    private ArrayList<ItemOrder> listItems;

    private String customerName;
    private String cashierName;

    public SellReceipt()
    {
        setCategory(Category.SELL);
        setReceiptID("RE0000");
        setCustomerName("1");
        setListItems(new ArrayList<>());
        setTotalCost(0);
        setRemark("1");
        setDate(LocalDateTime.now().toLocalDate());
        setCashierName("1");
    }

    public SellReceipt(String _receiptID, Enum<Category> _category, String _customerName, String _cashierName, double _totalCost, LocalDate _date, String _remark, ArrayList<ItemOrder> _listItems)
    {
        setCategory(_category);
        setReceiptID(_receiptID);
        setCustomerName(_customerName);

        setTotalCost(_totalCost);
        setRemark(_remark);
        setDate(_date);
        setCashierName(_cashierName);

        setListItems(_listItems);
    }

    public void calculateTotalCost()
    {
        for(ItemOrder item: getListItems())
        {
            double cost = item.getProduct().getSellingPrice() * item.getAmount() * (1 - (item.getProduct().getDiscount() / 100));
            setTotalCost(getTotalCost() + cost);
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }



    public String toString()
    {
        String text = "";
        text = text.concat(getReceiptID());
        text = text.concat("|");

        text = text.concat(getCategory().toString());
        text = text.concat("|");

        text = text.concat(getCustomerName());
        text = text.concat("|");

        text = text.concat(getCashierName());
        text = text.concat("|");

        text = text.concat(String.valueOf(getTotalCost()));
        text = text.concat("|");

        text = text.concat(getDate().toString());
        text = text.concat("|");

        text = text.concat(App.stringToAscii(getRemark()));
        text = text.concat(" |");

        text = text.concat(String.valueOf(getListItems().size()));
        text = text.concat("\n");

        for(ItemOrder i: listItems)
        {
            text = text.concat(String.valueOf(i.getAmount()));
            text = text.concat("|");

            text = text.concat(i.getProduct().toString());
            text = text.concat("\n");
        }

        return text;
    }
}
