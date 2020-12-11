package Entity;

import java.io.Serializable;
import java.util.*;

public class Calendar implements Serializable {

    private Date date;
    // key is the start time, value is the list of eventId that starts ar that time.
    private Map<Integer, ArrayList<Integer>> schedule;

    public Calendar(Date date) {
        this.date = date;
        schedule = new HashMap<>();
        for (int i = 9; i < 17; i++) {
            schedule.put(i, new ArrayList<>());
        }
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, ArrayList<Integer>> getSchedule() {
        return schedule;
    }

}
