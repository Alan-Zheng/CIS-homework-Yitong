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
    public Label nameLabel, phoneLabel, emailLabel, birthLabel, groupLabel;

    private final FileHandler dataAccess = new FileHandler();

    /**
     * Configure the create page.
     * Triggered when user enters the page.
     */
    @SuppressWarnings("DuplicatedCode")
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
                dataAccess.append(switch (f.group()) {
                    case "Families" -> FileHandler.FAMILY_PATH;
                    case "Close friends" -> FileHandler.CLOSE_PATH;
                    case "General friends" -> FileHandler.GENERAL_PATH;
                    default -> "";
                }, f);
                msg.setText("Friend added.");
            } catch (IOException e) {
                msg.setText("An error occurred.");

                System.err.println("[FATAL] data lost: failed to open file when trying to write.");
            }
        }

        // Cleanup
        firstName.clear();
        lastName.clear();
        phone.clear();
        email.clear();
        month.setText("Month");
        date.setText("Date");
        group.setText("Group");
    }

    /**
     * Delete the selected friend from the friend book.
     * Triggered when the "delete" button on view page is clicked.
     */
    public void onDelete () {
        try {
            Friend f = friends.getSelectionModel().getSelectedItem();
            for (Friend fr : dataAccess.getLoaded()) {
                if (fr.toString().equals(f.toString())) {
                    dataAccess.getLoaded().remove(fr);
                    dataAccess.rewrite(fr.group());

                    break;
                }
            }

            clearView();
            display();
        } catch (IOException e) {
            System.err.println("[FATAL] data lost: failed to open file when trying to write,");
        } catch (NullPointerException e) { /* skip */ }
    }

    /**
     * Display the loaded list of friends to {@code ListView}.
     * Triggered when any radio button is clicked.
     */
    public void display () {
        clearView();

        ArrayList<String> loadPrep = new ArrayList<>();
        if (familySelect.isSelected()) loadPrep.add(FileHandler.FAMILY_PATH);
        if (closeSelect.isSelected()) loadPrep.add(FileHandler.CLOSE_PATH);
        if (generalSelect.isSelected()) loadPrep.add(FileHandler.GENERAL_PATH);

        try {
            dataAccess.load(loadPrep.toArray(new String[0]));

            friends.getItems().clear();
            friends.getItems().addAll(dataAccess.getLoaded());
        } catch (IOException e) {
            System.err.println("[FATAL] data lost: failed to open file when trying to access.");
        }
    }

    /**
     * Display detailed information of a selected friend.
     * Triggered when the list is clicked.
     */
    public void displayEach () {
        try {
            Friend f = friends.getSelectionModel().getSelectedItem();
            nameLabel.setText(f.name());
            phoneLabel.setText(f.phone());
            emailLabel.setText(f.email());
            birthLabel.setText(f.birthday());
            groupLabel.setText(f.group());
        } catch (NullPointerException e) { /* skip */ }
    }

    /**
     * Clear all the labels.
     * Triggered when the user get into or leave the "view" section. Can be called from other methods.
     */
    public void clearView () {
        nameLabel.setText("");
        phoneLabel.setText("");
        emailLabel.setText("");
        birthLabel.setText("");
        groupLabel.setText("");
    }
}
