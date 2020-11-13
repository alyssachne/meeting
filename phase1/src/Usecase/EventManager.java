package Usecase;

import Entity.*;

import java.util.ArrayList;

public class EventManager {

    ArrayList<Event> allEvents;

    /**
     * Creates an empty talk manager.
     */
    public EventManager() {
        allEvents = new ArrayList<>();
    }
//    /**
//     * Adds a given event to this event manager.
//     * @param event: the event being added to the ArrayList.
//     */
//    public void addEvent(Event event) {
//        allEvents.add(event);
//    }

    /**
     * Create a new event.
     * @param id: the id of the event.
     */
    public void createEvent(int id, String title, int time, int roomId){
        Event event = new Event(id, title, time, roomId);
        allEvents.add(event);
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

    public void setTime(int id, int time){
        getEvent(id).setTime(time);
    }
    /**
     * Add the attendee to this event and return true if successfully added in. Else, return false.
     * @param event: the event the attendee is going to attend.
     * @param attendee: the the attendee who is going to attend this event.
     */
    public boolean addAttendee(Event event, Attendee attendee) {
        String name = attendee.getUsername();
        return event.addAttendee(name);}

    /**
     * Remove attendee from this event and return true if successfully removed. Else, return false.
     * @param event: the event the attendee is going to be removed from.
     * @param attendee: the the attendee who is going to cancel his spot.
     */
    public boolean cancelSpot(Event event, Attendee attendee) {
        String name = attendee.getUsername();
        return event.removeAttendee(name);
    }

    // Get a list of event this person signed up for.
    public ArrayList<Event> getSignUp(String username) {
        ArrayList<Event> acc = new ArrayList<>();
        for (Event event: allEvents) {
            if(event.ListOfAttendees.contains(username)) {
                acc.add(event);
            }
        }
        return acc;
    }

}
