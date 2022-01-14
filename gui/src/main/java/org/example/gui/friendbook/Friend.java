package org.example.gui.friendbook;

public class Friend {
    private final String firstName, lastName;
    private final String phone;
    private final String birthday;

    public Friend (String firstName, String lastName, String phone, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthday = birthday;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public String getPhone () {
        return phone;
    }

    public String getBirthday () {
        return birthday;
    }
}
