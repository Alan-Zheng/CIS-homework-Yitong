package org.example.gui.friendsbook;

/**
 * It always gave the warning that this should be converted into a {@code Record} so I did.
 */
public record Friend (String name, String phone, String email, String birthday,
                     String group) {
    @Override
    public String toString () {
        return String.format("%15s | %s", group, name);
    }
}
