package practice.variable;

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
            sb.append(String.format("| %d | ", i + 1))
                    .append(String.format("%30s", courses.get(i)[0]))
                    .append(" | ")
                    .append(String.format("%15s", courses.get(i)[1]))
                    .append(" |\n");

        sb.append('+').append("-".repeat(54)).append("+\n");

        System.out.println(sb);
    }
}
