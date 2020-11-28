package Usecase;

import Entity.Event;
import Entity.Talk;

import java.io.Serializable;
import java.util.List;

public class TalkManager extends EventManager implements Serializable {

    /**
     * Create a new talk. The length of speakers should be 1.
     *
     * @param id: the id of the event.
     */
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int maxCapacity) {
        Event talk = new Talk(id, title, time, roomId, speakers, maxCapacity);
        allEvents.add(talk);
    }
}
