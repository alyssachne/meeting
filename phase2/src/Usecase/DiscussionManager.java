package Usecase;

import Entity.Discussion;
import Entity.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiscussionManager extends EventManager implements Serializable {

    public ArrayList<Event> allDiscussions;
    /**
     * Create a new talk. The length of speakers should be greater than 1.
     *
     * @param id : the id of the event.
     * @param constraints
     */
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int duration,
                            int maxCapacity, String eventAccess, List<String> constraints) {
        Event discussion = new Discussion(id, title, time, roomId, speakers, duration, maxCapacity, eventAccess, constraints);
        allDiscussions.add(discussion);
    }

    public boolean addSpeaker(Integer eventId, String username) {
        if(getEvent(eventId).getSpeaker().contains(username)) {
            return false;
        }
        getEvent(eventId).getSpeaker().add(username);
        return true;
    }

    // remove the speaker if this person is in the speaker list and there are more than 2 people in the list, otherwise
    // it is not a discussion.
    public boolean removeSpeaker(Integer eventId, String username) {
        if(getEvent(eventId).getSpeaker().contains(username) && getEvent(eventId).getSpeaker().size() >= 2) {
            getEvent(eventId).getSpeaker().remove(username);
        }
        return false;
    }

    public boolean cancelEvent(int id) {
        if(allDiscussions.contains(getEvent(id))) {
            allDiscussions.remove(getEvent(id));
            return true;
        }
        System.out.println("This discussion does not exist.");
        return false;
    }

    /**
     * Return the event of the corresponding id, raise error if not found.
     * @param id: the id of the event.
     */
    public Event getEvent(int id) {
        try {
            for (Event event: allDiscussions) {
                if(event.getId() == id) {
                    return event;
                }
            }
        }
        catch (Exception e) {
            System.out.println("This discussion does not exist.");
        }
        return null;
    }

    public boolean containEvent(int id){
        if(allDiscussions.contains(id)) {
            return true;
        }
        return false;
    }
}
