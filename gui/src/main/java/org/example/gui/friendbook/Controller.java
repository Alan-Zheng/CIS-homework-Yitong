package org.example.gui.friendbook;

import javafx.scene.control.*;

import java.util.NoSuchElementException;

public class Controller {
    public Label firstName, lastName, phone, email, birthday, msg;
    public TextField firstNameField, lastNameField, phoneField, emailField;
    public MenuButton month, date;
    public Button done;

    public ListView<Friend> friends = new ListView<>();
    public Label nameLabel, phoneLabel, emailLabel, birthLabel;

    // Store information about adding / deleting friends.
    private char mode = ' ';

    public void onCreateBtnClick () {
        mode = 'A';

        firstName.setVisible(true);
        lastName.setVisible(true);
        firstNameField.setVisible(true);
        lastNameField.setVisible(true);
        phone.setVisible(true);
        phoneField.setVisible(true);
        email.setVisible(true);
        emailField.setVisible(true);
        birthday.setVisible(true);
        month.setVisible(true);
        date.setVisible(true);

        done.setVisible(true);
        msg.setText("");

        // Generate dropdown
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
    }

    public void onDeleteBtnClick () {
        mode = 'D';

        firstName.setVisible(true);
        lastName.setVisible(true);
        firstNameField.setVisible(true);
        lastNameField.setVisible(true);
        phone.setVisible(false);
        phoneField.setVisible(false);
        email.setVisible(false);
        emailField.setVisible(false);
        birthday.setVisible(false);
        month.setVisible(false);
        date.setVisible(false);

        done.setVisible(true);
        msg.setText("");
    }

    public void onDone () {
        firstName.setVisible(false);
        lastName.setVisible(false);
        firstNameField.setVisible(false);
        lastNameField.setVisible(false);
        phone.setVisible(false);
        phoneField.setVisible(false);
        email.setVisible(false);
        emailField.setVisible(false);
        birthday.setVisible(false);
        month.setVisible(false);
        date.setVisible(false);

        done.setVisible(false);

        // Add or delete a friend.
        if (mode == 'A') {
            if (month.getText().equals("Month") || date.getText().equals("Date") || firstNameField.getText().equals("")
                    || lastNameField.getText().equals("") || phoneField.getText().equals("")
                    || emailField.getText().equals(""))
                msg.setText("Please fill in all the information.");
            else {
                friends.getItems().add(
                        new Friend(firstNameField.getText(), lastNameField.getText(),
                                phoneField.getText(), emailField.getText(), month.getText() + ' ' + date.getText()));

                msg.setText("Friend added.");
            }
        } else if (mode == 'D') {
            try {
                friends.getItems().remove(friends.getItems().stream()
                        .filter(o -> o.getName().equals(firstNameField.getText() + ' ' + lastNameField.getText()))
                        .findAny().orElseThrow());
                msg.setText("Friend deleted.");
            } catch (NoSuchElementException e) {
                msg.setText("The friend is not in the friend book.");
            }
        }

        // Cleanup
        mode = ' ';

        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        month.setText("Month");
        date.setText("Date");
    }

    public void display () {
        try {
            Friend f = friends.getSelectionModel().getSelectedItem();
            nameLabel.setText(f.getName());
            phoneLabel.setText(f.getPhone());
            emailLabel.setText(f.getEmail());
            birthLabel.setText(f.getBirthday());
        } catch (NullPointerException e) { /* skip */ }
    }

    public void clearView () {
        nameLabel.setText("");
        phoneLabel.setText("");
        emailLabel.setText("");
        birthLabel.setText("");
    }
}
