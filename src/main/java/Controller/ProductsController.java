package Controller;

import Model.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductsController implements Initializable {
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, Integer> remainingColumn;
    @FXML private TableColumn<Product, Double> salespriceColumn;
    @FXML private TableColumn<Product, Double> importpriceColumn;
    @FXML private TableColumn<Product, String> statusColumn;
    @FXML private TableColumn<Product, String> nationColumn;

    @FXML private JFXButton searchButton;
    @FXML private JFXButton addButton;
    @FXML private JFXButton editButton;
    @FXML private JFXButton deleteButton;

    @FXML private JFXTextField nameTextField;
    @FXML private JFXComboBox<String> categoryComboBox;
    @FXML private JFXComboBox<String> nationComboBox;
    @FXML private JFXCheckBox isDeletedCheckBox;


    //ProductsManager productsManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(e -> addButtonOnClick());
        searchButton.setOnAction(e -> searchButtonOnClick());
        editButton.setOnAction(e -> editButtonOnClick());
        deleteButton.setOnAction(e -> deleteButtonOnClick());

        productsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

    }

    public void bindTableData()
    {
//        idColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
//            Product p = cdf.getValue();
//            return new SimpleStringProperty(p.getProductID());
//        });
        idColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        remainingColumn.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        salespriceColumn.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
        nationColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));
        importpriceColumn.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        statusColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String isActive;
            if(p.isActive()) isActive = "Active";
            else isActive = "Inactive";

            return new SimpleStringProperty(isActive);
        });
//        statusColumn.setCellValueFactory(cellData -> cellData.getValue().);

        //productsManager = new ProductsManager();
        //System.out.println(productsManager.getProducts());
        productsTable.setItems(App.dataManager.getProductsManager().getProducts());
        System.out.println(productsTable.getColumns());
    }


    private void searchButtonOnClick()
    {
        String _idOrName = nameTextField.getText();
        String _category = categoryComboBox.getSelectionModel().getSelectedItem();
        String _nation = nationComboBox.getSelectionModel().getSelectedItem();
        boolean _isDeleted = isDeletedCheckBox.selectedProperty().get();
        System.out.println(_idOrName + " " + _category + " " + _nation + " " + _isDeleted);
    }

    private void addButtonOnClick()
    {
        //System.out.println(addButton.getText());
        //App.dataManager.getProductsManager().getProducts().add(new Product());
        displayAddBox();
    }

    private void displayAddBox()
    {
        JFXComboBox test;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Products/Add.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);
        test = (JFXComboBox) scene.lookup("#testComboBox");
        test.setEditable(true);
        test.getItems().addAll(
                "Hello",
                "2",
                "3",
                "4",
                "5"
                );

        window.setScene(scene);

        window.showAndWait();
    }


    private void editButtonOnClick()
    {
        System.out.println("Edit...");
    }

    private void deleteButtonOnClick()
    {
        System.out.println("Delete...");

        ObservableList<Product> selectedList = productsTable.getSelectionModel().getSelectedItems();

        int selectedListLength = selectedList.toArray().length;

        if(selectedListLength != 0)
        {
            boolean selection = displayDeleteConfirmBox(selectedListLength);

            if(selection)
            {
//            App.dataManager.getProductsManager().getProducts().add(new Product());

                selectedList.forEach(App.dataManager.getProductsManager().getProducts()::remove);
            }
        }



    }

    private boolean displayDeleteConfirmBox(int noOfSelectedRows)
    {
        AtomicBoolean selection = new AtomicBoolean(false);
        JFXButton yesButton;
        JFXButton noButton;
        Label warningLabel;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete");
        window.setMinWidth(250);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Products/Delete.fxml"));
        VBox deleteLayout = null;
        try {
            deleteLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(deleteLayout);
        yesButton = (JFXButton) scene.lookup("#yesButton");
        noButton = (JFXButton) scene.lookup("#noButton");
        warningLabel = (Label) scene.lookup("#warningLabel");

        if(noOfSelectedRows == 1)
        {
            warningLabel.setText("Are you sure to delete this product?");
        }
        else
        {
            warningLabel.setText("Are you sure to delete" + " these " + noOfSelectedRows + " product?");
        }

        yesButton.setOnAction(e -> {
            selection.set(true);
            window.close();
        });

        noButton.setOnAction(e -> {
            selection.set(false);
            window.close();
        });

        window.setScene(scene);

        window.showAndWait();
        return selection.get();
    }



}
