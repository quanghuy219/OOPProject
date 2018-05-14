package controller.employees;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import controller.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.employee.Employee;
import model.employee.Level;
import model.employee.Status;

public class AddUpdateEmployeeController {

    private Stage stage;

    @FXML private Label idLabel;
    @FXML private TextField nameTextField;
    @FXML private JFXComboBox<String> statusComboBox;
    @FXML private JFXComboBox<String> levelComboBox;
    @FXML private JFXDatePicker datePicker;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField salaryTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    @FXML private JFXButton saveButton;
    @FXML private JFXButton cancelButton;

    private Employee employee;

    public void init(Employee _employee)
    {
        this.employee = _employee;
        setDetail();
        handleButtons();
    }

    private void setDetail()
    {
//        if(!employee.getEmployeeID().equals("EE0000"))
//            employee.setEmployeeID(App.dataManager.getEmployeesManager().getNextEmployeeID());

        idLabel.setText(employee.getEmployeeID());
        nameTextField.setText(employee.getName());

        statusComboBox.getItems().addAll(App.getEnumConstants(Status.class));
        statusComboBox.setValue(employee.getStatus().toString());
        levelComboBox.getItems().addAll(App.getEnumConstants(Level.class));
        levelComboBox.setValue(employee.getLevel().toString());

        datePicker.setValue(employee.getDob());

        phoneNumberTextField.setText(employee.getPhoneNumber());
        salaryTextField.setText(String.format("%.0f", employee.getSalary()));
        usernameTextField.setText(employee.getUsername());
        passwordTextField.setText(employee.getPassword());
    }

    private void handleButtons()
    {
        saveButton.setOnAction(e -> {
            Employee employee = new Employee();
            employee.setEmployeeID(idLabel.getText());
            employee.setName(nameTextField.getText());
            employee.setStatus(Status.valueOf(statusComboBox.getValue()));
            employee.setLevel(Level.valueOf(levelComboBox.getValue()));
            employee.setDob(datePicker.getValue());
            employee.setPhoneNumber(phoneNumberTextField.getText());
            employee.setSalary(Double.parseDouble(salaryTextField.getText()));
            employee.setUsername(usernameTextField.getText());
            employee.setPassword(passwordTextField.getText());

            App.dataManager.getEmployeesManager().addUpdateEmployee(employee);
            getStage().close();
        });

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
