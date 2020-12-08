package Entity;

import java.io.Serializable;
import java.util.*;

public class Talk extends Event implements Serializable {
    /**
     * Class constructor.
     *
     * @param id       The id of the event.
     * @param title    The title of the event.
     * @param date     The starting time of the event.
     * @param roomId   The id of the room of the event.
     * @param speakers The username of the speaker who talks at the event.
     */
    public Talk(int id, String title, Date date, int roomId, List<String> speakers, int duration, String eventAccess,
                List<String> constraints) {
        super(id, title, date,roomId, speakers, duration, eventAccess,constraints);
    }
}
