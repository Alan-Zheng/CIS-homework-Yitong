package org.example.gui.friendsbook;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    static final String FAMILY_PATH = "friends-book\\families.txt",
            CLOSE_PATH = "friends-book\\close.txt",
            GENERAL_PATH = "friends-book\\general.txt";

    private BufferedReader br;
    private FileReader fr;
    private PrintWriter pw;
    private FileWriter fw;

    /**
     * Load data from selected group file(s) and convert them into {@code Friend} objects.
     *
     * @param files One or more files that is going to be loaded.
     * @return the complete list of friends loaded from files.
     * @throws IOException if failed to open one or more files.
     */
    ArrayList<Friend> load (String... files) throws IOException {
        ArrayList<Friend> friendList = new ArrayList<>();

        for (String file : files) {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");

                friendList.add(new Friend(info[0], info[1], info[2], info[3], info[4]));
            }
        }

        return friendList;
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

        pw.printf("%s,%s,%s,%s\n", friend.getName(), friend.getPhone(), friend.getEmail(), friend.getBirthday());

        pw.close();
    }
}
