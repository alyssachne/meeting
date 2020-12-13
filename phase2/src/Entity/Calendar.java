package Entity;

import java.io.Serializable;
import java.util.*;

public class Calendar implements Serializable {

    // dd/mm/yyyy
    private String date;
    // key is the start time, value is the list of eventId that starts ar that time.
    private Map<Date, ArrayList<Integer>> schedule;

    /**
     * Initialize a Calendar
     * @param date: the date of the Calendar
     */
    public Calendar(String date) {
        this.date = date;
        schedule = new HashMap<>();
        for (int i = 9; i < 17; i++) {
            Date d = new Date((Integer.parseInt(date.substring(6,9))-1900), (Integer.parseInt(date.substring(3,5))-1)
                    , Integer.parseInt(date.substring(0,2)));
            d.setHours(i);
            schedule.put(d, new ArrayList<>());
        }
    }

    /**
     * Get the date of the Calendar
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the schedule of the date
     */
    public Map<Date, ArrayList<Integer>> getSchedule() {
        return schedule;
    }

}
