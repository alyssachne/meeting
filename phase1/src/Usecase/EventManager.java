package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Miscellaneous {@link Event} methods
 *
 *<p>
 *     This class provides the essential functionalities associates with {@link Event} class, such
 *     as creating an Event object and set relavent instance variables. It also provides services to
 *     look for these information inside an entity Event object.
 *</p>
 *
 **/

public abstract class EventManager implements Serializable {

    public ArrayList<Event> allEvents;

    /**
     * Creates an empty talk manager.
     */
    public EventManager() {
        allEvents = new ArrayList<>();
    }

    /**
     * Create a new event.
     * @param id: the id of the event.
     */
    public abstract void createEvent(int id, String title, int time, int roomId, List<String> speakers, int maxCapacity);

    public boolean cancelEvent(int id){
        if(allEvents.contains(getEvent(id))) {
            allEvents.remove(getEvent(id));
            return true;
        }
        return false;
    }

    /**
     * Return the event of the corresponding id, raise error if not found.
     * @param id: the id of the event.
     */
    public Event getEvent(int id) {
        try {
            for (Event event: allEvents) {
                if(event.getId() == id) {
                    return event;
                }
            }
        }
        catch (Exception e) {
            System.out.println("This talk does not exist.");
        }
        return null;
    }

    /**
     * Set the strat time of the selected event.
     * @param id: the id of the event.
     * @param time: the new start time of the event.
     */
    public void setTime(int id, int time){
        getEvent(id).setTime(time);
    }

    /**
     * Add the attendee to this event and return true if successfully added in. Else, return false.
     * @param username: the username of the attendee who is going to attend this event.
     * @param eventId: the unique Id of the event the attendee is going to attend.
     */
    public boolean addAttendee(String username, int eventId) {
        // if the event is full or the username is in the list
        if(getEvent(eventId).isFull() | getEvent(eventId).getAttendees().contains(username)) {
            return false;
        }
        getEvent(eventId).getAttendees().add(username);
        return true;
    }

    /**
     * Remove attendee from this event and return true if successfully removed. Else, return false.
     * @param username: the username of the attendee who is going to cancel his spot.
     * @param eventId: the unique Id of the event the attendee is going to be removed from.
     */
    public boolean cancelSpot(String username, int eventId) {
        for (String name: getEvent(eventId).getAttendees()) {
            if(username.equals(name)) {
                getEvent(eventId).getAttendees().remove(name);
                return true;
            }
        }
        return false;
    }

}
