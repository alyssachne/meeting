package Entity;

import java.io.Serializable;
import java.util.List;

public class VipEvent extends Event implements Serializable {
    /**
     * Class constructor.
     *
     * @param id          The id of the event.
     * @param title       The title of the event.
     * @param time        The starting time of the event.
     * @param roomId      The id of the room of the event.
     * @param speakers    The username of the speaker who talks at the event.
     * @param duration    The duration of the event.
     * @param maxCapacity The maximum capacity of the event.
     * @param eventAccess:-
     * @param constraints:-
     */
    public VipEvent(int id, String title, int time, int roomId, List<String> speakers, int duration, int maxCapacity, String eventAccess, List<String> constraints) {
        super(id, title, time, roomId, speakers, duration, maxCapacity, eventAccess, constraints);
    }
//    /**
//     * Class constructor.
//     *  @param id      The id of the event.
//     * @param title   The title of the event.
//     * @param time    The starting time of the event.
//     * @param roomId  The id of the room of the event.
//     * @param speaker The username of the speaker who talks at the event.
//     */
//    public VipEvent(int id, String title, int time, int roomId, List<String> speaker) {
//        super(id, title, time, roomId, speaker);
//    }
//
//    /**
//     * To override the toString method to describe an event by including its details.
//     *
//     * @return The string description of an event.
//     */
//    @Override
//    public String toString() {
//        String s = getSpeaker();
//        return "Event ID:"+ id +" This event is about " + title + ", given by " + s + ". It starts at " + time + " on Room"
//                + roomId + " and it lasts for 1 hour." ;
//    }
//
//    /**
//     * This method allows the use case class to get the type of event, mainly used to realize the function that allows
//     * VIP users to sign up for exclusive events.
//     * To be implemented in {@link Event} and {@link VipEvent}
//     */
//    @Override
//    public String getType() {
//        return "VIP_Event";
//    }
}
