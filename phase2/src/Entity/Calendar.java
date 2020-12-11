package Entity;

import java.io.Serializable;
import java.util.*;

public class Calendar implements Serializable {

    private Date date;
    // key is the start time, value is the list of eventId that starts ar that time.
    private Map<Integer, ArrayList<Integer>> schedule;

    /**
     * Initialize a Calendar
     * @param date: the date of the Calendar
     */
    public Calendar(Date date) {
        this.date = date;
        schedule = new HashMap<>();
        for (int i = 9; i < 17; i++) {
            schedule.put(i, new ArrayList<>());
        }
    }

    /**
     * Get the date of the Calendar
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the schedule of the date
     */
    public Map<Integer, ArrayList<Integer>> getSchedule() {
        return schedule;
    }

}
