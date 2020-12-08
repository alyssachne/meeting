//package Entity;
//
//import java.io.Serializable;
//import java.util.*;
//
//public class Discussion extends Event implements Serializable {
//    private List<String> speakers;
//    /**
//     * Class constructor.
//     *
//     * @param id       The id of the event.
//     * @param title    The title of the event.
//     * @param date     The start time of the event.
//     * @param roomId   The id of the room of the event.
//     * @param speakers The username of the speaker who talks at the event.
//     */
//    public Discussion(int id, String title, Date date, int roomId, List<String> speakers, int duration, String eventAccess,
//                      List<String> constraints) {
//        super(id, title, date, roomId, duration, eventAccess,constraints);
//    }
//
//    /**
//     * Get the username of the speaker who talks at the event.
//     * @return speaker The username of the speaker who talks at the event.
//     */
//    public List<String> getSpeaker() {
//        return speakers;
//    }
//
//
//}