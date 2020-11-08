package Entity;

import javafx.util.Pair;

import java.util.ArrayList;

public class Room {
    public int MaxCapacity;
    public int id;
    public Pair event;
    public boolean full;
    public boolean booked;
    public ArrayList<String> ListOfAttendees;

    public Room(int id, int MaxCapacity) {
        this.MaxCapacity = MaxCapacity;
        this.id = id;
    }

    // Get the maximum capacity of the room.
    public double getMaxCapacity() {
        return MaxCapacity;
    }

    // Get the current capacity of the room.
    public double getCurrentCapacity() {
        return ListOfAttendees.size();
    }

    // Get the id of this room.
    public int getId() {
        return id;
    }

    // Check whether this room is full or not.
    public boolean isFull() {
        return full;
    }

    // Check whether this room is booked or not.
    public boolean isBooked() {return booked;}

    // If this room is not booked, change the state to Book, modify the Event and return True.
    public boolean Book(Integer id, String title) {
        if(!booked) {
            booked = true;
            event = new Pair(id, title);
            return true;
        }
        return false;
    }

    /**
     * Add the attendee to this room and return true if the attendee is not in the ListOfAttendees or the room is not
     * full. Else, return false. Change the state of fullness if current capacity equals maximum capacity of the room.
     * @param username: the username of the attendee who attends to the event.
     */
    public boolean addAttendee(String username) {
        if(full) {
            return false;}
        for (String name: ListOfAttendees) {
            if(name.equals(username)){
                return false;}
        }
        ListOfAttendees.add(username);
        if(getCurrentCapacity() == MaxCapacity) {
            full = true;}
        return true;
    }


    /**
     * Remove the attendee from this room and return true if the attendee is in the ListOfAttendees. Else, return false.
     * @param username: the username of the attendee who is going to be removed from the ListOfAttendees.
     */
    public boolean removeAttendee(String username) {
        for (String name : ListOfAttendees) {
            if (name.equals(username)) {
                ListOfAttendees.remove(username);
                full = false;
                return true;
            }
        }
            return false;
        }
}
