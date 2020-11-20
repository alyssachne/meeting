package Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room implements Serializable {
    private final int MaxCapacity;
    private final int id;
    //time, eventId
    private final HashMap<Integer, Integer> schedule = new HashMap<>(8);

    /**
     * Class constructor
     * @param id The id of the room.
     * @param MaxCapacity The max capacity of the room.
     */
    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
        }
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

}