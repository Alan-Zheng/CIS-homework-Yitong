package assignments.no9buildclass;

@SuppressWarnings("unused")
public class Employee {
    private final int id;
    private final String firstName, lastName;
    private int salary;

    public Employee (int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId () { return id; }

    public String getFirstName () { return firstName; }

    public String getLastName () { return lastName; }

    public String getName () {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public int getSalary () { return salary; }

    public void setSalary (int salary) { this.salary = salary; }

    public int getAnnualSalary () { return this.salary * 12; }

    public int raiseSalary (int percent) {
        this.salary *= (1 + percent / 100.0);
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Employee[id=%d, name='%s', salary=%d]", this.id, this.getName(), this.salary);
    }
}
