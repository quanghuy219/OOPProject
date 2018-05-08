package Controller.Sales;

import Model.Sales.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceiptManager {
    private String currentReceiptID;
    private ObservableList<Receipt> listReceipts;

    public ReceiptManager()
    {
        currentReceiptID = "RE0000";
        listReceipts = FXCollections.observableArrayList();
    }

    public void addReceipt(Receipt _receipt)
    {
        currentReceiptID = getNextReceiptID();
        _receipt.setReceiptID(currentReceiptID);
        listReceipts.add(_receipt);
    }


    public String getNextReceiptID()
    {
        int currentNumber = Integer.valueOf(currentReceiptID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);

        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextReceiptID = "RE".concat(nextNumber);
        return nextReceiptID;
    }
}
