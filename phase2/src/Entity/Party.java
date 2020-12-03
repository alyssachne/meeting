package Entity;

import java.io.Serializable;
import java.util.List;

public class Party extends Event implements Serializable {
    /**
     * Class constructor.
     *
     * @param id       The id of the event.
     * @param title    The title of the event.
     * @param time     The starting time of the event.
     * @param roomId   The id of the room of the event.
     * @param speakers The username of the speaker who talks at the event.
     */
    public Party(int id, String title, int time, int roomId, List<String> speakers, int duration, int maxCapacity,
                 String eventAccess) {
        super(id, title, time, roomId, speakers, duration, maxCapacity, eventAccess);
    }
}
