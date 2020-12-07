package Usecase;

import Entity.Event;
import Entity.Talk;

import java.io.Serializable;
import java.util.*;

public class TalkManager extends EventManager implements Serializable {

    public ArrayList<Event> allTalks;
    /**
     * Create a new talk. The length of speakers should be 1.
     *
     * @param id : the id of the event.
     * @param constraints
     */
    public void createEvent(int id, String title, Date date, int time, int roomId, List<String> speakers, int duration,
                            int maxCapacity, String eventAccess, List<String> constraints) {
        Event talk = new Talk(id, title,date, time, roomId, speakers, duration, maxCapacity, eventAccess, constraints);
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

    @Override
    public List<Integer> getAllEvents() {
        List<Integer> all = new ArrayList<>();
        for(Event e: allTalks) {
            all.add(e.getId());
        }
        return all;
    }

    public boolean containEvent(int id) {
        if(allTalks.contains(id)) {
            return true;
        }
        return false;
    }
}
