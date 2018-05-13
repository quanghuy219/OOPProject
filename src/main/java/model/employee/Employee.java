package model.employee;

import controller.App;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Employee {
    private String employeeID;
    private String name;
    private Enum<Status> status;
    private Enum<Level> level;
    private LocalDate dob;
    private String phoneNumber;
    private double salary;
    private String username;
    private String password;

    public Employee(String _employeeID, String _name, Enum<Status> _status, Enum<Level> _level, LocalDate _dob, String _phoneNumber, double _salary, String _username, String _password)
    {
        this.setEmployeeID(_employeeID);
        this.setName(_name);
        this.setStatus(_status);
        this.setLevel(_level);
        this.setDob(_dob);
        this.setPhoneNumber(_phoneNumber);
        this.setSalary(_salary);
        this.setUsername(_username);
        this.setPassword(_password);
    }

    public Employee()
    {
        this.setEmployeeID(App.dataManager.getEmployeesManager().getNextEmployeeID());
        this.setName("");
        this.setStatus(Status.ACTIVE);
        this.setLevel(Level.EMPLOYEE);
        this.setDob(LocalDate.now());
        this.setPhoneNumber("");
        this.setSalary(0);
        this.setUsername(getEmployeeID());
        this.setPassword("");
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<Level> getLevel() {
        return level;
    }

    public void setLevel(Enum<Level> level) {
        this.level = level;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public String toString()
    {
        String text = "";
        text = text.concat(getEmployeeID()).concat("|");
        text = text.concat(getName()).concat("|");
        text = text.concat(getStatus().toString()).concat("|");
        text = text.concat(getLevel().toString()).concat("|");
        text = text.concat(getDob().toString()).concat("|");
        text = text.concat(getPhoneNumber()).concat("|");
        text = text.concat(String.valueOf(getSalary())).concat("|");
        text = text.concat(getUsername()).concat("|");
        text = text.concat(getPassword());

        return text;
    }

    public static Employee valueOf(String text)
    {
        Employee e = new Employee();
        String[] parts = text.split(Pattern.quote("|"));
        e.setEmployeeID(parts[0]);
        e.setName(parts[1]);
        e.setStatus(Status.valueOf(parts[2]));
        e.setLevel(Level.valueOf(parts[3]));
        e.setDob(LocalDate.parse(parts[4]));
        e.setPhoneNumber(parts[5]);
        e.setSalary(Double.parseDouble(parts[6]));
        e.setUsername(parts[7]);
        e.setPassword(parts[8]);

        return e;
    }
}
