package Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Room implements Serializable {
    public int MaxCapacity;
    public int id;
    //time, eventId
    public HashMap<Integer, Integer> schedule = new HashMap<>(8);

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

    // Get a list of available time of this room.
    public ArrayList<Integer> getAvailableTime() {
        ArrayList<Integer> available = new ArrayList<>();
        for (HashMap.Entry<Integer,Integer> time: schedule.entrySet()) {
            if(time.getValue() == null) {
                available.add(time.getKey());
        }}
            return available;
    }

    // If this room is not booked at the given time, add the event to the schedule, add the list of attendee to the
    // ListOfAttendee, and return True. Else, return false.
    public boolean book(Integer eventId, Integer time) {
        if(!isBooked(time)) {
            schedule.put(time, eventId);
            return true;
        }
        return false;
    }

    public boolean cancel(Integer eventId) {
        Set<Integer> temp = schedule.keySet();
        for(Integer time: temp) {
            if(schedule.get(time).equals(eventId)) {
                schedule.replace(time, null);
                return true;
            }
        }
        return false;
    }
}