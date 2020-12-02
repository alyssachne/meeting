package Usecase;

import Entity.Event;
import Entity.Party;
import Entity.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyManager extends EventManager implements Serializable {


    public ArrayList<Event> allParties;
    /**
     * Create a new talk. The length of speakers should be 0.
     *
     * @param id: the id of the event.
     */
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int maxCapacity) {
        Event party = new Party(id, title, time, roomId, speakers, maxCapacity);
        allParties.add(party);
    }

    public boolean cancelEvent(int id) {
        if(allParties.contains(getEvent(id))) {
            allParties.remove(getEvent(id));
            return true;
        }
        System.out.println("This party does not exist.");
        return false;
    }

    /**
     * Return the event of the corresponding id, raise error if not found.
     * @param id: the id of the event.
     */
    public Event getEvent(int id) {
        try {
            for (Event event: allParties) {
                if(event.getId() == id) {
                    return event;
                }
            }
        }
        catch (Exception e) {
            System.out.println("This party does not exist.");
        }
        return null;
    }

    public boolean containEvent(int id) {
        if(allParties.contains(id)) {
            return true;
        }
        return false;
    }
}
