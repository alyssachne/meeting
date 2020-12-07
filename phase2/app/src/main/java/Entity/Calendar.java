package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar implements Serializable {

    private Date date;
    // key is the start time, value is the list of eventId that starts ar that time.
    private Map<Integer, List<Integer>> schedule = new HashMap<>(8);

    public Calendar(Date date) {
        this.date = date;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
        };
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, List<Integer>> getSchedule() {
        return schedule;
    }

}
