package controller.cashflow;

/*
* Controller handles binding data from cash flow Manager to displayed table
*/

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import controller.App;
import controller.expenses.ExpensesController;
import controller.inventory.InventoryController;
import controller.sales.SalesController;
import controller.sales.SalesManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.VBox;
import model.cashflow.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CashflowController implements Initializable {

    @FXML private TableView<Entry> entriesTable;
    @FXML private TableColumn<Entry, String> dateColumn;
    @FXML private TableColumn<Entry, String> revenueColumn;
    @FXML private TableColumn<Entry, String> inventoryColumn;
    @FXML private TableColumn<Entry, String> expenseColumn;


    @FXML private JFXDatePicker fromDate;
    @FXML private JFXDatePicker toDate;

    @FXML private Button resetButton;

    @FXML private Label totalRevenueLabel;
    @FXML private Label totalProductsSoldLabel;

    @FXML private Label totalInventoryLabel;
    @FXML private Label totalProductsBoughtLabel;

    @FXML private Label totalExpensesLabel;
    @FXML private Label totalProfitLabel;

    @FXML private JFXButton revenueDetailButton;
    @FXML private JFXButton inventoryDetailButton;
    @FXML private JFXButton expensesDetailButton;

    @FXML private VBox detailBox1;
    @FXML private VBox detailBox2;

    private FilteredList<Entry> filteredData;
    private Entry selectedEntry;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindEntriesTable();
        configureEntriesTable();

        clearDetail();
        handleDetailButtons();
        handleResetButton();
    }

    private void bindEntriesTable()
    {
        // format data shown in the column of the table
        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty( e.getDate().toString() );
        });

        revenueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty(String.format("%.0f", e.getRevenue()));
        });

        inventoryColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty(String.format("%.0f", e.getInventory()));
        });

        expenseColumn.setCellValueFactory((TableColumn.CellDataFeatures<Entry, String> cdf) -> {
            Entry e = cdf.getValue();
            return new SimpleStringProperty(String.format("%.0f", e.getExpense()));
        });


        ObservableList<Entry> listEntries = App.dataManager.getCashflowManager().getListEntries();
        filteredData = new FilteredList<>(listEntries, p -> true);

        // Display data between selected dates

        ObjectProperty<Predicate<Entry>> fromDateFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Entry>> toDateFilter = new SimpleObjectProperty<>();

        fromDateFilter.bind(Bindings.createObjectBinding(() ->
                entry -> {
                    LocalDate date = fromDate.getValue();
                    if (date == null) return true;
                    return (entry.getDate().isAfter(date) || entry.getDate().isEqual(date));

                }, fromDate.valueProperty()));

        toDateFilter.bind(Bindings.createObjectBinding(() ->
                entry -> {
                    LocalDate date = toDate.getValue();

                    if (date == null) return true;

                    return (entry.getDate().isBefore(date) || entry.getDate().isEqual(date));

                }, toDate.valueProperty()));

        filteredData.predicateProperty().bind(Bindings.createObjectBinding(
                () -> fromDateFilter.get().and(toDateFilter.get()),
                fromDateFilter, toDateFilter));


        SortedList<Entry> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(entriesTable.comparatorProperty());

        // bind data to table
        entriesTable.setItems(sortedData);

        toDate.valueProperty().addListener((obs, oldSelection, newSelection) -> clearDetail());

        fromDate.valueProperty().addListener((obs, oldSelection, newSelection) -> clearDetail());

    }

    /*
    *   Catch select event from user
    *   Show detail if user click to choose an entry
     */

    private void configureEntriesTable()
    {
        entriesTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        entriesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedEntry = newSelection;
                setDetail(newSelection);
            } else {
                selectedEntry = null;
                clearDetail();
            }
        });
    }

    /*
    *   Show detail information for the selected entry
     */

    private void setDetail(Entry _entry)
    {

        detailBox2.setVisible(true);

        totalRevenueLabel.setText(String.format("%.0f", _entry.getRevenue()));
        totalProductsSoldLabel.setText(Integer.toString(_entry.getNo0fProductsSold()));

        totalInventoryLabel.setText(String.format("%.0f", _entry.getInventory()));
        totalProductsBoughtLabel.setText(Integer.toString(_entry.getNo0fProductsBought()));

        totalExpensesLabel.setText(String.format("%.0f", _entry.getExpense()));

        totalProfitLabel.setText(String.format("%.0f", _entry.getRevenue() -  _entry.getInventory() - _entry.getExpense()));
    }

    /*
    *  Show total amount of revenue, expenses, inventory on starting
    *  Clear detail from the selected date and reset to the total amount
     */
    private void clearDetail()
    {
        detailBox2.setVisible(false);

        double totalRevenue = 0, totalInventory = 0, totalExpense = 0, totalProfit;
        int totalProductsSold = 0, totalProductsBought = 0;

        for(Entry entry: filteredData)
        {
            totalRevenue += entry.getRevenue();
            totalInventory += entry.getInventory();
            totalExpense += entry.getExpense();

            totalProductsSold += entry.getNo0fProductsSold();
            totalProductsBought += entry.getNo0fProductsBought();
        }

        totalProfit = totalRevenue - totalInventory - totalExpense;

        totalRevenueLabel.setText(String.format("%.0f", totalRevenue));
        totalProductsSoldLabel.setText(Integer.toString(totalProductsSold));

        totalInventoryLabel.setText(String.format("%.0f",totalInventory));
        totalProductsBoughtLabel.setText(Integer.toString(totalProductsBought));

        totalExpensesLabel.setText(String.format("%.0f", totalExpense));

        totalProfitLabel.setText(String.format("%.0f", totalProfit));
    }

    // navigate to corresponding page when button detail is clicked

    private void handleDetailButtons()
    {
        /*
        * send the current date to the sales page
        * display data in the selected date
        */
        revenueDetailButton.setOnAction(e -> {
            SalesController salesController = App.sceneManager.getLoader("Sales").getController();
            salesController.setDates(selectedEntry.getDate(), selectedEntry.getDate());

            App.sceneManager.setPaneContent("Sales");
        });

        /*
        * Navigate to the Inventory page and display data from the selected date
         */

        inventoryDetailButton.setOnAction(e -> {
            InventoryController inventoryController = App.sceneManager.getLoader("Inventory").getController();
            inventoryController.setDates(selectedEntry.getDate(), selectedEntry.getDate());

            App.sceneManager.setPaneContent("Inventory");
        });

        /*
        * Navigate to expense page and display data from the selected date
         */

        expensesDetailButton.setOnAction(e -> {
            ExpensesController expensesController = App.sceneManager.getLoader("Expenses").getController();
            expensesController.setDates(selectedEntry.getDate(), selectedEntry.getDate());

            App.sceneManager.setPaneContent("Expenses");
        });
    }

    /*
    * remove date in the date pickers
    * display all the data in the manager
     */
    private void handleResetButton()
    {
        resetButton.setOnAction(e -> {
            fromDate.setValue(null);
            toDate.setValue(null);
        });
    }
}
