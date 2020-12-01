package Usecase;

import Entity.Event;
import Entity.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TalkManager extends EventManager implements Serializable {

    public ArrayList<Event> allTalks;
    /**
     * Create a new talk. The length of speakers should be 1.
     *
     * @param id: the id of the event.
     */
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int maxCapacity) {
        Event talk = new Talk(id, title, time, roomId, speakers, maxCapacity);
        allTalks.add(talk);
    }

    public boolean cancelEvent(int id) {
        if(allTalks.contains(getEvent(id))) {
            allTalks.remove(getEvent(id));
            return true;
        }
        System.out.println("This talk does not exist.");
        return false;
    }

    /**
     * Return the event of the corresponding id, raise error if not found.
     * @param id: the id of the event.
     */
    public Event getEvent(int id) {
        try {
            for (Event event: allTalks) {
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

    public boolean containEvent(int id) {
        if(allTalks.contains(id)) {
            return true;
        }
        return false;
    }
}
