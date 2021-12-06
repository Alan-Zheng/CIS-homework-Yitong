package assignments.no10school;

import java.util.ArrayList;

/**
 * The main class of the assignment.
 */
public class Main {
    /**
     * The main method.
     *
     * @param args Required.
     */
    public static void main (String[] args) {
        // Create a new school
        School school = new School("University Hill Secondary School", "UHS",
                "3228 Ross Drive, Vancouver BC, Canada");

        // Generate some students
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student tmp = new Student("fn" + (char) (i + 65), "ln" + (char) (i + 65), i + 1);
            students.add(tmp);
            school.addStudent(tmp);
        }

        // Generate some teachers
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Teacher tmp = new Teacher("fn" + (char) (i + 65), "ln" + (char) (i + 65), "CS");
            teachers.add(tmp);
            school.addTeacher(tmp);
        }

        school.showStudents();
        school.showTeachers();

        // Remove the following: Student[name='fnC lnC', grade=3]; Student[name='fnF lnF', grade=6].
        school.deleteStudent(students.get(2));
        school.deleteStudent(students.get(5));

        // Remove the following: Teacher[name='fnA lnA', subject='CS'].
        school.deleteTeacher(teachers.get(0));

        school.showStudents();
        school.showTeachers();
    }
}
