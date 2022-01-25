package org.example.gui.friendsbook;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    static final String FAMILY_PATH = "friends-book\\families.txt",
            CLOSE_PATH = "friends-book\\close.txt",
            GENERAL_PATH = "friends-book\\general.txt";

    private final ArrayList<Friend> loaded = new ArrayList<>();

    private PrintWriter pw;
    private FileWriter fw;

    public ArrayList<Friend> getLoaded () {
        return loaded;
    }

    /**
     * Load data from selected group file(s) and convert them into {@code Friend} objects.
     *
     * @param files One or more files that is going to be loaded.
     * @throws IOException if failed to open one or more files.
     */
    void load (String... files) throws IOException {
        loaded.clear();

        for (String file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");

                loaded.add(new Friend(info[0], info[1], info[2], info[3], info[4]));
            }
        }
    }

    /**
     * Append a new friend to a dedicated group file.
     *
     * @param file The path of the group file; must be one of the three variables specified in {@code FileHandler}.
     * @param friend The {@code Friend} object being added.
     * @throws IOException if failed to open the file.
     */
    void append (String file, Friend friend) throws IOException {
        fw = new FileWriter(file, true);
        pw = new PrintWriter(fw);

        pw.printf("%s,%s,%s,%s,%s\n",
                friend.name(), friend.phone(), friend.email(), friend.birthday(), friend.group());

        pw.close();
    }

    /**
     * Overwrite the friend list of a dedicated category.
     *
     * @param category The category of the group; must be one of the three following string (case-sensitive):
     *                 <ul><li>Families</li>
     *                 <li>Close friends</li>
     *                 <li>General friends</li></ul>
     * @throws IOException if failed to open the category.
     */
    void rewrite (String category) throws IOException {
        fw = new FileWriter(switch (category) {
            case "Families" -> FileHandler.FAMILY_PATH;
            case "Close friends" -> FileHandler.CLOSE_PATH;
            case "General friends" -> FileHandler.GENERAL_PATH;
            default -> "";
        }, false);
        pw = new PrintWriter(fw);

        loaded.stream().filter(f -> f.group().equals(category)).forEach(f ->
                pw.printf("%s,%s,%s,%s,%s\n", f.name(), f.phone(), f.email(), f.birthday(), f.group()));

        pw.close();
    }
}
