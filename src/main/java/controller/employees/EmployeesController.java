package controller.employees;

import controller.App;
import controller.FinalPaths;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import model.employee.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class EmployeesController implements Initializable {
    @FXML private TableView<Employee> employeesTable;
    @FXML private TableColumn<Employee, String> idColumn;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, String> statusColumn;
    @FXML private TableColumn<Employee, String> levelColumn;
    @FXML private TableColumn<Employee, String> dobColumn;
    @FXML private TableColumn<Employee, String> phoneNumberColumn;
    @FXML private TableColumn<Employee, String> salaryColumn;
    @FXML private TableColumn<Employee, String> usernameColumn;
    @FXML private TableColumn<Employee, String> passwordColumn;

    @FXML private JFXButton resetButton;
    @FXML private JFXButton addButton;
    @FXML private JFXButton editButton;
    @FXML private JFXButton deleteButton;

    @FXML private JFXComboBox<String> levelComboBox;

    @FXML private JFXTextField searchTextField;

    private FilteredList<Employee> filteredData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        administerUserPermission();

        addButton.setOnAction(e -> displayAddBox());
        editButton.setOnAction(e -> displayEditBox());
        deleteButton.setOnAction(e -> deleteButtonOnClick());

        editButton.setDisable(true);
        deleteButton.setDisable(true);
        
        bindTableData();

        setLevelComboBox();
        handleResetButton();
    }

    private void administerUserPermission()
    {
        if(App.getUser().getLevel() == Level.EMPLOYEE)
        {
            salaryColumn.setVisible(false);
            usernameColumn.setVisible(false);
            passwordColumn.setVisible(false);

            salaryColumn.setPrefWidth(0);
            usernameColumn.setPrefWidth(0);
            passwordColumn.setPrefWidth(0);
            //employeesTable.setPrefWidth(840);

            addButton.setDisable(true);
            editButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    private void bindTableData()
    {
        employeesTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        if(App.getUser().getLevel() != Level.EMPLOYEE)
        {
            employeesTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Employee> c) -> {
                int numberSelections = c.getList().toArray().length;

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
            });
        }

        idColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getEmployeeID());
        });

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getName());
        });

        statusColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getStatus().toString());
        });

        levelColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getLevel().toString());
        });


        dobColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getDob().toString());
        });

        phoneNumberColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getPhoneNumber());
        });

        salaryColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(String.format("%.0f", e.getSalary()));
        });

        usernameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getUsername());
        });

        passwordColumn.setCellValueFactory((TableColumn.CellDataFeatures<Employee, String> cdf) -> {
            Employee e = cdf.getValue();
            return new SimpleStringProperty(e.getPassword());
        });

        ObservableList<Employee> listEmployees = App.dataManager.getEmployeesManager().getListEmployees();
        filteredData = new FilteredList<>(listEmployees, p -> true);

        ObjectProperty<Predicate<Employee>> nameFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Employee>> levelFilter = new SimpleObjectProperty<>();

        nameFilter.bind(Bindings.createObjectBinding(() ->
                employee -> {
                    String name = searchTextField.getText();
                    if(name == null || name.equals("")) return true;
                    if(employee.getEmployeeID().toLowerCase().contains(name.toLowerCase()) || employee.getName().toLowerCase().contains(name.toLowerCase()))
                        return true;
                    else return false;
                }, searchTextField.textProperty()));

        levelFilter.bind(Bindings.createObjectBinding(() ->
                employee -> {
                    if(levelComboBox.getValue() == null)
                        return true;
                    else if(Enum.valueOf(Level.class, levelComboBox.getValue()) == employee.getLevel())
                        return true;
                    else return false;
                }, levelComboBox.valueProperty()));

        filteredData.predicateProperty().bind(Bindings.createObjectBinding(
                () -> nameFilter.get().and(levelFilter.get()),
                nameFilter, levelFilter));


        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employeesTable.comparatorProperty());
        employeesTable.setItems(sortedData);
    }

    private void setLevelComboBox()
    {
        String[] listCategory = App.getEnumConstants(Level.class);
        for(String s: listCategory)
        {
            levelComboBox.getItems().add(s);
        }
    }

    private void displayAddBox()
    {
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Employee");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FinalPaths.EMPLOYEES_ADD_UPDATE_EMPLOYEE));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        AddUpdateEmployeeController addUpdateEmployeeController = (AddUpdateEmployeeController) fxmlLoader.getController();
        addUpdateEmployeeController.setStage(window);
        addUpdateEmployeeController.init(new Employee());


        window.setScene(scene);
        window.showAndWait();
    }

    private void displayEditBox()
    {
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Employee");
        window.setMinWidth(400);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FinalPaths.EMPLOYEES_ADD_UPDATE_EMPLOYEE));
        AnchorPane addLayout = null;
        try {
            addLayout = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(addLayout);

        Employee employee = employeesTable.getSelectionModel().getSelectedItem();

        AddUpdateEmployeeController addUpdateEmployeeController = (AddUpdateEmployeeController) fxmlLoader.getController();
        addUpdateEmployeeController.setStage(window);
        addUpdateEmployeeController.init(employee);

        window.setScene(scene);
        window.showAndWait();
    }

    private void deleteButtonOnClick()
    {
        int selection = App.displayConfirmBox("Are you sure to delete this employee ?");
        if(selection == 1)
        {
            Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
            App.dataManager.getEmployeesManager().getListEmployees().remove(selectedEmployee);
            refreshTable();
        }
    }

    public void refreshTable()
    {
        employeesTable.refresh();
    }

    private void handleResetButton()
    {
        resetButton.setOnAction(e -> {
            levelComboBox.setValue(null);
            searchTextField.setText("");
            refreshTable();
        });


    }

}
