
interface Department {
    void assignDepartment(String deptName);
    String getDepartmentDetails();
}


abstract class Employee implements Department {
    private String employeeId;
    private String name;
    private double baseSalary;
    private String department;
    
    public Employee(String employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

 
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public void assignDepartment(String deptName) {
        this.department = deptName;
    }

    @Override
    public String getDepartmentDetails() {
        return department;
    }

    public abstract double calculateSalary();

    public void displayDetails() {
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Calculated Salary: $" + calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double benefits;

    public FullTimeEmployee(String employeeId, String name, double baseSalary, double benefits) {
        super(employeeId, name, baseSalary);
        this.benefits = benefits;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + benefits;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String employeeId, String name, double baseSalary, int hoursWorked, double hourlyRate) {
        super(employeeId, name, baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (hoursWorked * hourlyRate);
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
       
        Employee emp1 = new FullTimeEmployee("FT001", "Alice", 60000, 10000);
        emp1.assignDepartment("Engineering");

        Employee emp2 = new PartTimeEmployee("PT002", "Bob", 15000, 60, 100);
        emp2.assignDepartment("Customer Support");

       
        Employee[] employeeList = { emp1, emp2 };

        for (Employee emp : employeeList) {
            emp.displayDetails();
          
        }
    }
}
