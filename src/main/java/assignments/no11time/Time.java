package assignments.no11time;

/**
 * A {@code Time} class that holds information about a time.
 * Any invalid input of time will be reset to 0 with message printed to the console.
 */
public class Time {
    private int hour, minute, second;

    /**
     * Construct a time.
     * The constructor will validate parameters at last.
     *
     * @param hour Hour (range 0-23).
     * @param minute Minute (range 0-59).
     * @param second Second (range 0-59).
     */
    public Time (int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        checkTime();
    }

    /**
     * Valid time according to the range.
     */
    private void checkTime () {
        if (this.hour > 23 || this.hour < 0) {
            this.hour = 0;
            System.err.println("WARNING: Invalid hour being reset to 0.");
        }
        if (this.minute > 59 || this.minute < 0) {
            this.minute = 0;
            System.err.println("WARNING: Invalid minute being reset to 0.");
        }
        if (this.second > 59 || this.second < 0) {
            this.second = 0;
            System.err.println("WARNING: Invalid second being reset to 0.");
        }
    }

    public int getHour () {
        return hour;
    }

    public void setHour (int hour) {
        this.hour = hour;

        checkTime();
    }

    public int getMinute () {
        return minute;
    }

    public void setMinute (int minute) {
        this.minute = minute;

        checkTime();
    }

    public int getSecond () {
        return second;
    }

    public void setSecond (int second) {
        this.second = second;

        checkTime();
    }

    public void setTime (int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;

        checkTime();
    }

    /**
     * Add one second to the time.
     *
     * @return The instance.
     */
    public Time nextSecond () {
        this.second++;

        if (this.second > 59) {
            this.minute++;
            this.second = 0;
        }
        if (this.minute > 59) {
            this.hour++;
            this.minute = 0;
        }
        if (this.hour > 23) this.hour = 0;

        return this;
    }

    /**
     * Subtract one second to the time.
     *
     * @return The instance.
     */
    public Time previousSecond () {
        this.second--;

        if (this.second < 0) {
            this.minute--;
            this.second = 59;
        }
        if (this.minute < 0) {
            this.hour--;
            this.minute = 59;
        }
        if (this.hour < 0) this.hour = 0;

        return this;
    }

    @Override
    public String toString () {
        return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
    }
}
