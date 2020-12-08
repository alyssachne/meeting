package Usecase;

import Entity.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * @param id : the id of the event.
     */
    public void createEvent(int id, String title, Date date, int roomId, List<String> speakers, int duration,
                            String eventAccess,List<String> constraints) {
        if(speakers.size() == 0) {
            Event party = new Party(id,title,date,roomId,speakers,duration,eventAccess,constraints);
            allEvents.add(party);
        } else if(speakers.size() == 1) {
            Event talk = new Talk(id,title,date,roomId,speakers,duration,eventAccess,constraints);
            allEvents.add(talk);
        } else {
            Event discussion = new Discussion(id,title,date,roomId,speakers,duration,eventAccess,constraints);
            allEvents.add(discussion);
        }
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
        if(allEvents.contains(id)) {
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
     * @param username: the username of the attendee who is going to attend this event.
     * @param eventId: the unique Id of the event the attendee is going to attend.
     */
    public boolean addAttendee(String username, int eventId) {
        // if the username is in the list
        if(getEvent(eventId).getAttendees().contains(username)) {
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

    public String checkAccess(int eventId) {
        return getEvent(eventId).getEventAccess();
    }

    public boolean changeAccess(int eventId, String access) {
        if(getEvent(eventId).getEventAccess() == access){
            return false;
        }
        getEvent(eventId).setEventType(access);
        return true;
    }

}
