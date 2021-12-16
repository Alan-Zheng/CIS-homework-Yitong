package no10school;

/**
 * This class holds information about a {@code Teacher}.
 */
@SuppressWarnings("unused")
public class Teacher {
    private String firstName, lastName;
    private String subject;

    /**
     * Initialize a new teacher.
     *
     * @param firstName The teacher's first name.
     * @param lastName The teacher's last name.
     * @param subject The subject taught by the teacher.
     */
    public Teacher (String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }

    @Override
    public String toString () {
        return String.format("Name: %s %s; Subject: %s", this.firstName, this.lastName, this.subject);
    }
}
