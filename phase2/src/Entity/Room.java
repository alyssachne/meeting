package Entity;


import java.io.Serializable;
import java.util.*;

/**
 * The entity class for room
 *
 * This class stands for a room object, mainly used for event scheduling and check if event capacity is met.
 * Methods include getters and setters of a Room object as well as an isbooked method to check for availability.
 * A Hashmap is used to store the schedule of a room.
 */


/**
 * A room where events can take place.
 */
public class Room implements Serializable {
    private final int MaxCapacity;
    private final int id;
    //date, ArrayList of eventId
    private final Map<Date, ArrayList<Integer>> schedule;
    private ArrayList<String> constraints;

    private final HashMap<String, Integer> technology = new HashMap<>();

    /**
     * Class constructor
     * @param id The id of the room.
     * @param MaxCapacity The max capacity of the room.
     */
    public Room(int id, int MaxCapacity, ArrayList<String> constraints) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        this.constraints = constraints;
        this.schedule = new HashMap<>();
    }

    /**
     * Get the maximum capacity of the room.
     * @return The numeric value of the max capacity of the room.
     */
    public Integer getMaxCapacity() {
        return MaxCapacity;
    }

    /**
     * Get the id of this room.
     * @return The id of the room.
     */
    public int getId() {
        return id;
    }
//
//    /**
//     * Get the id of the event happens in this room at the given time.
//     * @param date The date the event happen.
//     * @return The id of the event happens in this room at the given time.
//     */
//    public ArrayList<Integer> getEvent(Date date) {
//        return schedule.get(date);
//    }
//
//    /**
//     * Check whether this room is booked at certain time or not.
//     * @param time The starting time of a certain time slot.
//     * @return The boolean value of whether this room is booked at certain time or not.
//     */
//    public boolean isBooked(Integer time) {
//        return schedule.get(time) != null;
//    }

    /**
     * Get the schedule of the room at the given date.
     * @return The hashmap of the schedule of a certain room.
     */
    public ArrayList<Integer> getSchedule(Date date) {
        return schedule.get(date);
    }

//    /**
//     * Get a ArrayList of available time of this room.
//     * @return An arraylist of available time of the room.
//     */
//    public ArrayList<Integer> getAvailableTime() {
//        ArrayList<Integer> available = new ArrayList<>();
//        for (HashMap.Entry<Integer,Integer> time: schedule.entrySet()) {
//            if(time.getValue() == null) {
//                available.add(time.getKey());
//        }}
//            return available;
//    }

    public ArrayList<String> getConstraints() {
        return this.constraints;
    }





    public HashMap<String, Integer> getTechnology() {
        return this.technology;
    }

}