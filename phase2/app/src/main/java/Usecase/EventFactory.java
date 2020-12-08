package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;

/**
 * Miscellaneous {@link Event} methods
 *
 *<p>
 *     This class provides the essential functionalities associates with {@link Event} class, such
 *     as creating an Event object and set relevant instance variables. It also provides services to
 *     look for these information inside an entity Event object.
 *</p>
 *
 **/

public class EventFactory implements Serializable {
    public ArrayList<Event> allEvents;

    /**
     * Create a new event.
     */
    public int createEvent(String title, Date date, int roomId, ArrayList<String> speakers, int duration,
                            String eventAccess,ArrayList<String> constraints) {
        Event e = new Event(allEvents.size() + 1, title, date, roomId, speakers, duration, eventAccess, constraints);
        return e.getId();
    };

    public boolean cancelEvent(int id) {
        if(allEvents.contains(getEvent(id))) {
            allEvents.remove(getEvent(id));
            return true;
        }
        System.out.println("This party does not exist.");
        return false;
    }

    public boolean containEvent(int id) {
        return allEvents.contains(id);
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
            System.out.println("This event does not exist.");
        }
        return null;
    }

    public ArrayList<Integer> getAllEvents() {
        ArrayList<Integer> all = new ArrayList<>();
        for(Event e: allEvents) {
            all.add(e.getId());
        }
        return all;
    }


    /**
     * Add the attendee to this event and return true if successfully added in. Else, return false.
     * @param username : the username of the attendee who is going to attend this event.
     * @param eventId : the unique Id of the event the attendee is going to attend.
     */
    public void addAttendee(String username, int eventId) {
        // if the username is in the ArrayList
        if(getEvent(eventId).getAttendees().contains(username)) {
            return;
        }
        getEvent(eventId).getAttendees().add(username);
    }

    /**
     * Remove attendee from this event and return true if successfully removed. Else, return false.
     * @param username : the username of the attendee who is going to cancel his spot.
     * @param eventId : the unique Id of the event the attendee is going to be removed from.
     */
    public void cancelSpot(String username, int eventId) {
        for (String name: getEvent(eventId).getAttendees()) {
            if(username.equals(name)) {
                getEvent(eventId).getAttendees().remove(name);
                return;
            }
        }
    }

    public String checkAccess(int eventId) {
        return getEvent(eventId).getEventAccess();
    }

    public boolean changeAccess(int eventId, String access) {
        if(getEvent(eventId).getEventAccess().equals(access)){
            return false;
        }
        getEvent(eventId).setEventType(access);
        return true;
    }

    public ArrayList<String> speakerOfEvent(int eventId) {
        return getEvent(eventId).getSpeaker();
    }

    public boolean containSpeaker(int eventId, String speaker) {
        return speakerOfEvent(eventId).contains(speaker);
    }

}
