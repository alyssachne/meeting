package Usecase;

import Entity.Calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarManager implements Serializable {

    public ArrayList<Calendar> allCalendars;

    /**
     * Initialize a CalendarManager
     */
    public CalendarManager() {
        allCalendars = new ArrayList<>();
    }

    /**
     * Get the Calendar
     * @param date: the date of Calendar
     * @return the whole calendar
     */
    public Calendar getCalendar(String date) {
        for (Calendar c : allCalendars) {
            if (c.getDate().equals(date)) {
                return c;
            }
        }
        Calendar c = new Calendar(date);
        allCalendars.add(c);
        return c;
    }

    /**
     * Given the selected date and events happen in that date, we can tell when the room, or the speaker (depends on
     * which class call this method in controller) are available.
     * @param date: the date to check for room and speaker availability
     * @param events: the event
     * @return a List of time that is available
     */
    public List<Date> getAvailable(String date, List<Integer> events) {
        List<Date> available = new ArrayList<>();
        for (Date d: getCalendar(date).getSchedule().keySet()) {
            if (!getCalendar(date).getSchedule().get(d).containsAll(events) && !events.isEmpty()){
                available.add(d);
            } else if(events.isEmpty()){
                available.add(d);
                }
            }
        return available;
    }

    /**
     * Create a new event and add into the calendar
     * @param date: the date of the event
     * @param time: the time of the Event
     * @param eventId: the Id of the Event
     */
    public void newEvent (String date, Date time, int eventId) {
        getCalendar(date).getSchedule().get(time).add(eventId);
    }

    /**
     * Cancel a event from the Calendar
     * @param date: the date of the Event
     * @param time: the time the Event start
     * @param eventId: the Id of the Event
     */
    public void cancelEvent (String date, Date time, int eventId) {
        for(Date d: getCalendar(date).getSchedule().keySet()){
            if(d.equals(time)){
                getCalendar(date).getSchedule().get(d).remove(Integer.valueOf(eventId));
            }
        }
    }
}
