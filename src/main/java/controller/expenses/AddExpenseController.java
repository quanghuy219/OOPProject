package controller.expenses;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import controller.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.expense.Expense;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class AddExpenseController implements Initializable {
    private Stage stage;
    private Expense expense;


    @FXML private Label expenseIDLabel;
    @FXML private TextField costTextField;
    @FXML private JFXDatePicker purchaseDate;
    @FXML private Label purchaserLabel;
    @FXML private TextArea remarkTextArea;

    @FXML private JFXButton saveButton;
    @FXML private JFXButton cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setExpenseIDLabel();
        setPurchaseDatePicker();
        setPurchaserLabel();
        handleSaveButton();
        handleCancelButton();
    }

    /*
    *   Auto set ID to new expense
     */
    private void setExpenseIDLabel()
    {
        String _expenseID = App.dataManager.getExpensesManager().getNextExpenseID();
        expenseIDLabel.setText(_expenseID);
    }

    private void setPurchaseDatePicker()
    {
        purchaseDate.setValue(LocalDate.now());
    }

    private void setPurchaserLabel()
    {
        purchaserLabel.setText(App.getUser().getName());
    }

    /*
    *   Validate input data
     */
    private void handleSaveButton()
    {
        costTextField.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue == null || newValue.equals("") || newValue.equals("0"))
            {
                saveButton.setDisable(true);
            }
            else saveButton.setDisable(false);
        });

        remarkTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null || newValue.equals(""))
            {
                saveButton.setDisable(true);
            }
            else saveButton.setDisable(false);
        });

        saveButton.setOnAction(e -> {
            int selection = App.displayConfirmBox("Are you sure to save this expense ?");
            if(selection == 1)
            {
                Expense expense = new Expense();
                expense.setExpenseID(expenseIDLabel.getText());
                expense.setCost(Double.valueOf(costTextField.getText()));
                expense.setPurchaseDate(purchaseDate.getValue());
                expense.setCreatedBy(purchaserLabel.getText());
                expense.setRemark(remarkTextArea.getText());
                App.dataManager.getExpensesManager().addExpense(expense);
                System.out.println("\""+remarkTextArea.getText()+"\"");
                System.out.println(App.stringToAscii(remarkTextArea.getText()));

                getStage().close();
            }
        });
    }

    private void handleCancelButton()
    {
        cancelButton.setOnAction(e -> {
            int selection = App.displayConfirmBox("Are you sure to cancel ?");
            if(selection == 1)
                getStage().close();
        });
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
