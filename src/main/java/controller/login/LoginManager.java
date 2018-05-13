package controller.login;

import controller.App;
import javafx.collections.ObservableList;
import model.employee.Employee;

public class LoginManager {
    public LoginManager()
    {

    }

    public Employee authenticate(String _username, String _password)
    {

        //if(_username.equals("1") && _password.equals("1")) return new Employee();

        ObservableList<Employee> listEmployees = App.dataManager.getEmployeesManager().getListEmployees();

        for(Employee e: listEmployees)
        {
            if(e.getUsername().equals(_username) && e.getPassword().equals(_password))
                return e;
        }

        return null;
    }
}
