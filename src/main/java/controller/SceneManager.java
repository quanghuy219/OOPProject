package controller;

import controller.products.ProductsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
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
    private FXMLLoader profitLoader;
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
        try {
            dashboardLoader = new FXMLLoader(new File("src/main/resources/View/Dashboard.fxml").toURI().toURL());
            dashboard = dashboardLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            salesLoader = new FXMLLoader(new File("src/main/resources/View/Sales.fxml").toURI().toURL());
            sales = salesLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        productsLoader = new FXMLLoader(getClass().getResource("/View/Products.fxml"));
        try {
            productsLoader = new FXMLLoader(new File("src/main/resources/View/Products.fxml").toURI().toURL());
            products = productsLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            customersLoader = new FXMLLoader(new File("src/main/resources/View/Customers.fxml").toURI().toURL());
            customers = customersLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            stockorderLoader = new FXMLLoader(new File("src/main/resources/View/Stockorder.fxml").toURI().toURL());
            stockorder = stockorderLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
//            inventoryLoader = new FXMLLoader(getClass().getResource("/View/Inventory.fxml"));
            inventoryLoader = new FXMLLoader(new File("src/main/resources/View/Inventory.fxml").toURI().toURL());
            inventory = inventoryLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
//            revenueLoader = new FXMLLoader(getClass().getResource("/View/Revenue.fxml"));
            revenueLoader = new FXMLLoader(new File("src/main/resources/View/Revenue.fxml").toURI().toURL());
            revenue = revenueLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
//            cashflowLoader = new FXMLLoader(getClass().getResource("/View/Cashflow.fxml"));
            cashflowLoader = new FXMLLoader(new File("src/main/resources/View/Cashflow.fxml").toURI().toURL());
            cashflow = cashflowLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            profitLoader = new FXMLLoader(new File("src/main/resources/View/Profit.fxml").toURI().toURL());

            profit = profitLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            settingsLoader = new FXMLLoader(new File("src/main/resources/View/Settings.fxml").toURI().toURL());
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


            if(trimmedMenuTitle.equals("Products"))
            {
                setProductsTable();
            }

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

            case "Profit":
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

            case "Profit":
            {
                result = profitLoader;
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



    private void setProductsTable()
    {
        ProductsController pc = productsLoader.getController();
        pc.bindTableData();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
