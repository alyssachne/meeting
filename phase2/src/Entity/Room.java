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
    //time, eventId
    private final HashMap<Integer, Integer> schedule = new HashMap<>(8);
    private List<String> constraints = new ArrayList<>();

    private final HashMap<String, Integer> technology = new HashMap<>();

    /**
     * Class constructor
     * @param id The id of the room.
     * @param MaxCapacity The max capacity of the room.
     */
    public Room(int id, int MaxCapacity, List<String> constraints) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
        }
        this.constraints = constraints;
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

    /**
     * Get the id of the event happens in this room at the given time.
     * @param time The starting time of an event.
     * @return The id of the event happens in this room at the given time.
     */
    public int getEvent(Integer time) {
        return schedule.get(time);
    }

    // return all events in this room.
    public Collection<Integer> getAllEvents() {
        return schedule.values();
    }

    /**
     * Check whether this room is booked at certain time or not.
     * @param time The starting time of a certain time slot.
     * @return The boolean value of whether this room is booked at certain time or not.
     */
    public boolean isBooked(Integer time) {
        return schedule.get(time) != null;
    }

    /**
     * Get the schedule of the room.
     * @return The hashmap of the schedule of a certain room.
     */
    public HashMap<Integer, Integer> getSchedule() {return schedule;}

    /**
     * Get a list of available time of this room.
     * @return An arraylist of available time of the room.
     */
    public ArrayList<Integer> getAvailableTime() {
        ArrayList<Integer> available = new ArrayList<>();
        for (HashMap.Entry<Integer,Integer> time: schedule.entrySet()) {
            if(time.getValue() == null) {
                available.add(time.getKey());
        }}
            return available;
    }

    public List<String> getConstraints() {
        return this.constraints;
    }





    public HashMap<String, Integer> getTechnology() {
        return this.technology;
    }

}