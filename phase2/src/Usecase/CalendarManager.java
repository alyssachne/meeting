package Usecase;
import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarManager implements Serializable {

    public List<Calendar> allCalendars;

//    public void createCalendar(Date date) {
//        Calendar c = new Calendar(date);
//        allCalendars.add(c);
//    }
    // when check the calendar, if the date has no event (every room and speaker are free at that day), create a new
    // calendar so that organizer can schedule a new event on that day.
    public Calendar getCalendar(Date date) {
        for (Calendar c : allCalendars) {
            if (c.getDate() == date) {
                return c;
            }
        }
        Calendar c = new Calendar(date);
        allCalendars.add(c);
        return c;
    }

    // given the selected date and events happen in that date, we can tell when the room, or the speaker (depends on
    // which class call this method in controller) are available.
    public List<Integer> getAvailable(Date date, List<Integer> events) {
        List<Integer> available = new ArrayList<>();
        for (Integer time: getCalendar(date).getSchedule().keySet()) {
            if (!getCalendar(date).getSchedule().get(time).containsAll(events)){
                available.add(time);
            }
        }
        return available;
    }

    public void newEvent (Date date, Integer time, int eventId) {
        getCalendar(date).getSchedule().get(time).add(eventId);
    }

    public void cancelEvent (Date date, Integer time, int eventId) {
        getCalendar(date).getSchedule().get(time).remove(eventId);
    }
}
