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
public class Room implements Serializable {
    private final int MaxCapacity;
    private final int id;
    //date, ArrayList of eventId
    private HashMap<String, ArrayList<Integer>> schedule;
    private ArrayList<String> constraints;

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

    /**
     * Get the schedule of the room at the given date.
     * @return The hashmap of the schedule of a certain room.
     */
    public ArrayList<Integer> getSchedule(String date) {
        if(!schedule.containsKey(date)){
            schedule.put(date, new ArrayList<>(8));
        }
        return schedule.get(date);
    }

    /**
     * Get the room's constraints
     */
    public ArrayList<String> getConstraints() {
        return this.constraints;
    }


}