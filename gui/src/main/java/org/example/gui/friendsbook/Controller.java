package org.example.gui.friendsbook;

import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public Label msg;
    public TextField firstName, lastName, phone, email;
    public MenuButton month, date, group;
    public Button done;

    public ListView<Friend> friends = new ListView<>();
    public RadioButton familySelect, closeSelect, generalSelect;
    public Label nameLabel, phoneLabel, emailLabel, birthLabel;

    private final FileHandler dataAccess = new FileHandler();

    /**
     * Configure the create page.
     * Triggered when user enters the page.
     */
    public void onCreate () {
        msg.setText("");

        // Generate date dropdown according to month
        for (MenuItem item : month.getItems()) {
            item.setOnAction(e -> {
                month.setText(item.getText());

                int numDays = switch (item.getText()) {
                    case "January", "March", "May", "July", "August", "October", "December" -> 31;
                    case "April", "June", "September", "November" -> 30;
                    case "February" -> 29;
                    default -> 0;
                };

                date.getItems().clear();

                for (int i = 1; i <= numDays; i++) {
                    int tmpNum = i;
                    MenuItem tmpItem = new MenuItem(String.valueOf(tmpNum));
                    tmpItem.setOnAction(event -> date.setText(String.valueOf(tmpNum)));
                    date.getItems().add(tmpItem);
                }
            });
        }

        for (MenuItem item : group.getItems())
            item.setOnAction(e -> group.setText(item.getText()));
    }

    /**
     * Add a friend to the friend book by writing the information into a file.
     * Triggered when the "done" button on create page is clicked.
     */
    public void onDone () {
        // Make sure every field is filled.
        if (month.getText().equals("Month") || date.getText().equals("Date") || group.getText().equals("Group")
                || firstName.getText().equals("") || lastName.getText().equals("") || phone.getText().equals("")
                || email.getText().equals(""))
            msg.setText("Please fill in all the information.");
        else {
            // Create a temp Friend object
            Friend f = new Friend(firstName.getText() + ' ' + lastName.getText(), phone.getText(), email.getText(),
                    month.getText() + ' ' + date.getText(), group.getText());

            try { // Prevent IOException
                dataAccess.append(switch (f.getGroup()) {
                    case "Families" -> FileHandler.FAMILY_PATH;
                    case "Close friends" -> FileHandler.CLOSE_PATH;
                    case "General friends" -> FileHandler.GENERAL_PATH;
                }, f);
                msg.setText("Friend added.");
            } catch (IOException e) {
                msg.setText("An error occurred.");

                System.err.println("[FATAL] data lost: failed to open file when trying to write.");
            }
        }
//        } else if (mode == 'D') {
//            try {
//                friends.getItems().remove(friends.getItems().stream()
//                        .filter(o -> o.getName().equals(firstNameField.getText() + ' ' + lastNameField.getText()))
//                        .findAny().orElseThrow());
//                msg.setText("Friend deleted.");
//            } catch (NoSuchElementException e) {
//                msg.setText("The friend is not in the friend book.");
//            }
//        }

        // Cleanup
        firstName.clear();
        lastName.clear();
        phone.clear();
        email.clear();
        month.setText("Month");
        date.setText("Date");
        group.setText("Group");
    }

    public void onDelete () {
        try {
            Friend f = friends.getSelectionModel().getSelectedItem();
        } catch (NullPointerException e) { /* skip */ }
    }

    /**
     * Display the loaded list of friends to {@code ListView}.
     * Triggered when any radio button is clicked.
     */
    public void display () {
        ArrayList<String> loadPrep = new ArrayList<>();
        if (familySelect.isSelected()) loadPrep.add(FileHandler.FAMILY_PATH);
        if (closeSelect.isSelected()) loadPrep.add(FileHandler.CLOSE_PATH);
        if (generalSelect.isSelected()) loadPrep.add(FileHandler.GENERAL_PATH);

        try {
            ArrayList<Friend> loadedList = dataAccess.load(loadPrep.toArray(new String[0]));
            friends.getItems().addAll(loadedList);
        } catch (IOException e) {
            System.err.println("[FATAL] data lost: failed to open file when trying to access.");
        }
    }

    public void displayEach () {
        try {
            Friend f = friends.getSelectionModel().getSelectedItem();
            nameLabel.setText(f.getName());
            phoneLabel.setText(f.getPhone());
            emailLabel.setText(f.getEmail());
            birthLabel.setText(f.getBirthday());
            // TODO group label
        } catch (NullPointerException e) { /* skip */ }
    }

    public void clearView () {
        nameLabel.setText("");
        phoneLabel.setText("");
        emailLabel.setText("");
        birthLabel.setText("");
    }
}
