package no10school;

import java.util.ArrayList;

/**
 * This class holds the information of a {@code School}.
 */
@SuppressWarnings("unused")
public class School {
    private final ArrayList<Teacher> teachers;
    private final ArrayList<Student> students;

    private String fullName, abbr, location;

    /**
     * Initialize a new school.
     * The constructor instantiate two new {@code ArrayList},
     * holding information about students and teachers in the school.
     *
     * @param fullName The full name of the school.
     * @param abbr The abbreviation of the school.
     * @param location The location of the school with street, city, province/state, and country.
     */
    public School (String fullName, String abbr, String location) {
        teachers = new ArrayList<>();
        students = new ArrayList<>();

        this.fullName = fullName;
        this.abbr = abbr;
        this.location = location;
    }

    public String getFullName () {
        return fullName;
    }

    public void setFullName (String fullName) {
        this.fullName = fullName;
    }

    public String getAbbr () {
        return abbr;
    }

    public void setAbbr (String abbr) {
        this.abbr = abbr;
    }

    public String getLocation () {
        return location;
    }

    public void setLocation (String location) {
        this.location = location;
    }

    /**
     * Add a {@code Teacher} to the school teacher list.
     *
     * @param teacher The teacher to be added.
     */
    public void addTeacher (Teacher teacher) {
        this.teachers.add(teacher);
    }

    /**
     * Add a {@code Student} to the school student list.
     *
     * @param student The student to be added.
     */
    public void addStudent (Student student) {
        this.students.add(student);
    }

    /**
     * Delete a {@code Teacher} from the school teacher list.
     *
     * @param teacher The teacher to be deleted.
     */
    public void deleteTeacher (Teacher teacher) {
        this.teachers.remove(teacher);
    }

    /**
     * Delete a {@code Student} from the school student list.
     *
     * @param student The teacher to be deleted.
     */
    public void deleteStudent (Student student) {
        this.students.remove(student);
    }

    /**
     * Print all teachers in the teacher list of the school.
     */
    public void showTeachers () {
        System.out.println("Teachers[");
        teachers.forEach(System.out::println);
        System.out.println("]\n");
    }

    /**
     * Print all students in the student list of the school.
     */
    public void showStudents () {
        System.out.println("Students[");
        students.forEach(System.out::println);
        System.out.println("]\n");
    }
}
