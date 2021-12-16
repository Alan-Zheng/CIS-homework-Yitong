package no10school;

/**
 * This class holds information about a {@code Student}.
 */
@SuppressWarnings("unused")
public class Student {
    private String firstName, lastName;
    private int grade;
    private int studentId;

    private static int idGen = 1;

    /**
     * Initialize a new student.
     * The constructor generates {@code studentId} based on {@code idGen} on construction.
     *
     * @param firstName The student's first name.
     * @param lastName The student's last name.
     * @param grade The student's current grade.
     */
    public Student (String firstName, String lastName, int grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentId = idGen++;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public int getGrade () {
        return grade;
    }

    public void setGrade (int grade) {
        this.grade = grade;
    }

    public int getStudentId () {
        return studentId;
    }

    public void setStudentId (int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString () {
        return String.format("Name: %s %s; Grade: %d", this.firstName, this.lastName, this.grade);
    }
}
