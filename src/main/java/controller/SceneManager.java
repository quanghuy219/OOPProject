package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private Stage mainStage;

    private Pane mainBodyPane;

    private FXMLLoader dashboardLoader;
    private FXMLLoader productsLoader;
    private FXMLLoader salesLoader;
    private FXMLLoader customersLoader;
    private FXMLLoader stockorderLoader;
    private FXMLLoader inventoryLoader;
    private FXMLLoader revenueLoader;
    private FXMLLoader cashflowLoader;
    private FXMLLoader expensesLoader;
    private FXMLLoader settingsLoader;


    private Node dashboard;
    private Node products;
    private Node sales;
    private Node customers;
    private Node stockorder;
    private Node inventory;
    private Node revenue;
    private Node cashflow;
    private Node profit;
    private Node settings;


    private String currentMenu;


    public SceneManager()
    {
        currentMenu = "xxx";
        setLoaders();
    }

    private void setLoaders()
    {
        dashboardLoader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
        try {
            dashboard = dashboardLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        salesLoader = new FXMLLoader(getClass().getResource("/view/sales/Sales.fxml"));
        try {
            sales = salesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productsLoader = new FXMLLoader(System.class.getResource("/view/products/Products.fxml"));
        try {
            products = productsLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        customersLoader = new FXMLLoader(getClass().getResource("/view/Customers.fxml"));
        try {
            customers = customersLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stockorderLoader = new FXMLLoader(getClass().getResource("/view/Stockorder.fxml"));
        try {
            stockorder = stockorderLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        inventoryLoader = new FXMLLoader(getClass().getResource("/view/inventory/Inventory.fxml"));
        try {
            inventory = inventoryLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        revenueLoader = new FXMLLoader(getClass().getResource("/view/Revenue.fxml"));
        try {
            revenue = revenueLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cashflowLoader = new FXMLLoader(getClass().getResource("/view/Cashflow.fxml"));
        try {
            cashflow = cashflowLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        expensesLoader = new FXMLLoader(getClass().getResource("/view/expenses/Expenses.fxml"));
        try {
            profit = expensesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        settingsLoader = new FXMLLoader(getClass().getResource("/view/Settings.fxml"));
        try {
            settings = settingsLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getProductsNode()
    {
        return products;
    }

    public Node getDashboardNode()
    {
        return dashboard;
    }

    public Pane getMainBodyPane() {
        return mainBodyPane;
    }

    public void setMainBodyPane(Pane mainBodyPane) {
        this.mainBodyPane = mainBodyPane;
    }

    public void setPaneContent(String menuTitle)
    {
        String trimmedMenuTitle = menuTitle.trim();
        System.out.println("Trimmed menu : "+trimmedMenuTitle);
        if(!trimmedMenuTitle.equals(currentMenu))
        {
            mainBodyPane.getChildren().removeAll(mainBodyPane.getChildren());

            mainBodyPane.getChildren().add(getNode(trimmedMenuTitle));

            currentMenu = trimmedMenuTitle;
        }
    }

    private Node getNode(String title)
    {
        Node result;
        String trimedTitle = title.trim();
        switch (trimedTitle)
        {
            case "Dashboard":
            {
                result = dashboard;
                break;
            }

            case "Sales":
            {
                result = sales;
                break;
            }

            case "Products":
            {
                result =  products;
                break;
            }

            case "Customers":
            {
                result = customers;
                break;
            }

            case "Stock order":
            {
                result = stockorder;
                break;
            }

            case "Inventory":
            {
                result = inventory;
                break;
            }

            case "Revenue":
            {
                result = revenue;
                break;
            }

            case "Cashflow":
            {
                result = cashflow;
                break;
            }

            case "Expenses":
            {
                result = profit;
                break;
            }

            case "Settings":
            {
                result = settings;
                break;
            }

            default:
            {
                result = null;
                break;
            }
        }

        return result;
    }

    public FXMLLoader getLoader(String name)
    {
        FXMLLoader result;
        switch (name)
        {
            case "Dashboard":
            {
                result = dashboardLoader;
                break;
            }

            case "Sales":
            {
                result = salesLoader;
                break;
            }

            case "Products":
            {
                result =  productsLoader;
                break;
            }

            case "Customers":
            {
                result = customersLoader;
                break;
            }

            case "Stock order":
            {
                result = stockorderLoader;
                break;
            }

            case "Inventory":
            {
                result = inventoryLoader;
                break;
            }

            case "Revenue":
            {
                result = revenueLoader;
                break;
            }

            case "Cashflow":
            {
                result = cashflowLoader;
                break;
            }

            case "Expenses":
            {
                result = expensesLoader;
                break;
            }

            case "Settings":
            {
                result = settingsLoader;
                break;
            }

            default:
            {
                result = null;
                break;
            }
        }
        return result;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


}
