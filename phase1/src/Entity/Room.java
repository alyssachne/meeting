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

    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
        }
    }

    // Get the maximum capacity of the room.
    public Integer getMaxCapacity() {
        return MaxCapacity;
    }

    // Get the id of this room.
    public int getId() {
        return id;
    }

    // Get the id of the event happens in this room at the given time.
    public int getEvent(Integer time) {
        return schedule.get(time);
    }

    // Check whether this room is booked at certain time or not.
    public boolean isBooked(Integer time) {
        return schedule.get(time) != null;
    }

    public HashMap<Integer, Integer> getSchedule() {return schedule;}

    // Get a list of available time of this room.
    public ArrayList<Integer> getAvailableTime() {
        ArrayList<Integer> available = new ArrayList<>();
        for (HashMap.Entry<Integer,Integer> time: schedule.entrySet()) {
            if(time.getValue() == null) {
                available.add(time.getKey());
        }}
            return available;
    }

}