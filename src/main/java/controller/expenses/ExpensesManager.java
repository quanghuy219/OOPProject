package controller.expenses;

import controller.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.expense.Expense;

public class ExpensesManager {
    private String currentExpenseID;
    private ObservableList<Expense> listExpenses;

    public ExpensesManager()
    {
        // ExpenseID will have the prefix 'ES...'
        currentExpenseID = "ES0000";
        setListExpenses(FXCollections.observableArrayList());
    }

    public void addExpense(Expense _expense)
    {
        getListExpenses().add(_expense);
        updateCurrentReceiptID(_expense.getExpenseID());
        App.dataManager.getCashflowManager().addExpense(_expense.getPurchaseDate(), _expense.getCost());
    }
    private void updateCurrentReceiptID(String _receiptID)
    {
        int currentNumber = Integer.valueOf(currentExpenseID.substring(2));
        int next = Integer.valueOf(_receiptID.substring(2));

        if(currentNumber < next)
        {
            currentExpenseID = _receiptID;
        }
    }

    public String getNextExpenseID()
    {
        int currentNumber = Integer.valueOf(currentExpenseID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);

        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextExpenseID = "ES".concat(nextNumber);
        return nextExpenseID;
    }

    public ObservableList<Expense> getListExpenses() {
        return listExpenses;
    }

    public void setListExpenses(ObservableList<Expense> listExpenses) {
        this.listExpenses = listExpenses;
    }
}
