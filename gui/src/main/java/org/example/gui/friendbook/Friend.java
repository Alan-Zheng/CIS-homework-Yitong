package org.example.gui.friendbook;

public class Friend {
    static int idGenerator = 1;

    private final int id;
    private final String firstName, lastName;
    private final String phone;
    private final String email;
    private final String birthday;

    public Friend (String firstName, String lastName, String phone, String email, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;

        id = idGenerator++;
    }

    public String getName () { return firstName + ' ' + lastName; }

    public String getPhone () {
        return phone;
    }

    public String getEmail () {
        return email;
    }

    public String getBirthday () {
        return birthday;
    }

    @Override
    public String toString () {
        return String.format("%4d | %s %s", id, firstName, lastName);
    }
}
