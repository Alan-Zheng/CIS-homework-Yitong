package assignments.no2variable;

import java.util.ArrayList;

public class Schedule {
    public static void main (String[] args) {
        ArrayList<String[]> courses = new ArrayList<>();

        courses.add(new String[]{"Pre Calculus 12", "Ms. Reed"});
        courses.add(new String[]{"AP Calculus AB", "Ms. Reed"});
        courses.add(new String[]{"English Studies", "Ms. Silvers"});
        courses.add(new String[]{"Computer Information System 12", "Mr. Zaremba"});

        StringBuilder sb = new StringBuilder();
        sb.append('+').append("-".repeat(54)).append("+\n");

        for (int i = 0; i < courses.size(); i++)
            sb.append(String.format("| %d | %30s | %15s |\n", i + 1, courses.get(i)[0], courses.get(i)[1]));

        sb.append('+').append("-".repeat(54)).append("+\n");

        System.out.println(sb);
    }
}
