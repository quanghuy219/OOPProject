package Controller;

import Controller.Products.UpdateProductController;
import Model.Product.Book;
import Model.Product.Mode;
import Model.Product.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductsController implements Initializable {
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> statusColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TableColumn<Product, Double> sellingPriceColumn;
    @FXML private TableColumn<Product, Double> buyingPriceColumn;
    @FXML private TableColumn<Product, Integer> discountColumn;
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

        addButton.setOnAction(e -> displayAddBox());
        searchButton.setOnAction(e -> searchButtonOnClick());
        editButton.setOnAction(e -> editButtonOnClick());
        deleteButton.setOnAction(e -> deleteButtonOnClick());

        editButton.setDisable(true);
        deleteButton.setDisable(true);

        handleRowSelection();
        bindTableData();

    }

    private void handleRowSelection()
    {

        productsTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

//        productsTable.getSelectionModel().getSelectedItems().addListener((obs, oldSelection, newSelection) -> {
//            System.out.println("selected : " + newSelection.getProductID());
//        });

        productsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Product> c) -> {
            int numberSelections = c.getList().toArray().length;

            System.out.println("select : "+numberSelections);
            if(numberSelections == 0)
            {
                editButton.setDisable(true);
                deleteButton.setDisable(true);
            }
            else if(numberSelections == 1)
            {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            else
            {
                editButton.setDisable(true);
                deleteButton.setDisable(false);
            }
        });

    }

    private void bindTableData()
    {
        idColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            return new SimpleStringProperty(p.getProductID());
        });

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            return new SimpleStringProperty(p.getName());
        });

        categoryColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String category = p.getCategory().toString().replace('_', ' ').toLowerCase();
            category = WordUtils.capitalizeFully(category);
            return new SimpleStringProperty(category);
        });

        statusColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String status = p.getStatus().toString();
            return new SimpleStringProperty(status);
        });

        quantityColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> cdf) -> {
            Product p = cdf.getValue();
            int quantity = p.getQuantity();

            return new SimpleIntegerProperty(quantity).asObject();
        });

        sellingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Double> cdf) -> {
            Product p = cdf.getValue();
            double sellingPrice = p.getSellingPrice();

            return new SimpleDoubleProperty(sellingPrice).asObject();
        });

        buyingPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Double> cdf) -> {
            Product p = cdf.getValue();
            double buyingPrice = p.getBuyingPrice();

            return new SimpleDoubleProperty(buyingPrice).asObject();
        });

        discountColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> cdf) -> {
            Product p = cdf.getValue();
            int discount = p.getDiscount();

            return new SimpleIntegerProperty(discount).asObject();
        });

        nationColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> cdf) -> {
            Product p = cdf.getValue();
            String nation = p.getNation().toString().replace('_', ' ').toLowerCase();
            nation = WordUtils.capitalizeFully(nation);

            return new SimpleStringProperty(nation);
        });

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

    private void displayAddBox()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Product");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Products/Update.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        UpdateProductController updateProductController = (UpdateProductController) fxmlLoader.getController();
        updateProductController.init(Mode.ADD, new Book());
        updateProductController.setParentStage(window);
        updateProductController.setParentScene(scene);

        window.setScene(scene);
        window.showAndWait();
    }

    private void displayEditBox()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit product");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Products/Update.fxml"));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        UpdateProductController updateProductController = (UpdateProductController) fxmlLoader.getController();
        updateProductController.init(Mode.EDIT, productsTable.getSelectionModel().getSelectedItem());
        updateProductController.setParentStage(window);
        updateProductController.setParentScene(scene);

        window.setScene(scene);
        window.showAndWait();
    }

    private void editButtonOnClick()
    {
        System.out.println("Edit...");
        displayEditBox();
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

    public void refreshTable()
    {
        productsTable.refresh();
    }

}
