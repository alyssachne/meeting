package Usecase;

import Entity.Discussion;
import Entity.Event;
import Entity.Party;

import java.io.Serializable;
import java.util.List;

public class DiscussionManager extends EventManager implements Serializable {

    /**
     * Create a new talk. The length of speakers should be greater than 1.
     *
     * @param id: the id of the event.
     */
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int maxCapacity) {
        Event discussion = new Discussion(id, title, time, roomId, speakers, maxCapacity);
        allEvents.add(discussion);
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
}
