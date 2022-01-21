package org.example.gui.friendsbook;

public class Friend {
    private final String name;
    private final String phone;
    private final String email;
    private final String birthday;
    private final String group;

    public Friend(String name, String phone, String email, String birthday, String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.group = group;
    }

    public String getName () {
        return name;
    }

    public String getPhone () {
        return phone;
    }

    public String getEmail () {
        return email;
    }

    public String getBirthday () {
        return birthday;
    }

    public String getGroup () {
        return group;
    }

    @Override
    public String toString () {
        return String.format("%15s | %s", group, name);
    }
}
