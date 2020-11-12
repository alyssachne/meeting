package Entity;


import java.util.ArrayList;
import java.util.Map;

public class Room {
    public int MaxCapacity;
    public int id;
    public Map<Integer, Integer> schedule;
    public Map<Integer, ArrayList> ListOfAttendees;

    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
        for (int i = 9; i < 16; i++) {
            schedule.put(i, null);
            ListOfAttendees.put(i, null);
        }
    }

    // Get the maximum capacity of the room.
    public double getMaxCapacity() {
        return MaxCapacity;
    }

    // Get the current capacity of the room at given time.
    public double getCurrentCapacity(Integer time) {
        return ListOfAttendees.get(time).size();
    }

    // Get the id of this room.
    public int getId() {
        return id;
    }

    // Check whether this room is full or not at the given time.
    public boolean isFull(Integer time) {
        return MaxCapacity == getCurrentCapacity(time);
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
    public ArrayList getAvailableTime() {
        ArrayList available = new ArrayList();
        for (Map.Entry<Integer,Integer> time: schedule.entrySet()) {
            if(time.getValue() == null) {
                available.add(time.getKey());
        }}
            return available;
    }

    // If this room is not booked at the given time, add the event to the schedule, add the list of attendee to the
    // ListOfAttendee, and return True. Else, return false.
    public boolean Book(Integer id, Integer time, ArrayList attendee) {
        if(!isBooked(time)) {
            schedule.put(time, id);
            ListOfAttendees.put(time, attendee);
            return true;
        }
        return false;
    }

    /**
     * Add the attendee to this room and return true if the attendee is not in the ListOfAttendees or the room is not
     * full. Else, return false. Change the state of fullness if current capacity equals maximum capacity of the room.
     * @param username: the username of the attendee who attends to the event.
     */
    public boolean addAttendee(String username, Integer time) {
        if(MaxCapacity == getCurrentCapacity(time)) {
            return false;}
        for (Map.Entry<Integer,ArrayList> name: ListOfAttendees.entrySet()) {
            if (name.equals(username)) {
                return false;
            }
        }
        ArrayList temp = ListOfAttendees.get(time);
        temp.add(username);
        return true;
    }


    /**
     * Remove the attendee from this room and return true if the attendee is in the ListOfAttendees. Else, return false.
     * @param username: the username of the attendee who is going to be removed from the ListOfAttendees.
     */
    public boolean removeAttendee(String username, Integer time) {
        for (Map.Entry<Integer,ArrayList> name: ListOfAttendees.entrySet()) {
            if (name.equals(username)) {
                return true;
            }
        }
            return false;
        }
}
