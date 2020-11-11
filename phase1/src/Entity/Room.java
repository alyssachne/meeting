package Entity;


import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Calendar;

public class Room {
    public int MaxCapacity;
    public int id;
    // The key is id and the value is duration of the event.
    public Pair<Integer, Integer> event;
    public boolean booked;
    public Calendar useTime;
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
        return MaxCapacity == getCurrentCapacity();
    }

    // Get the id of the event happens in this room.
    public Integer getEvent() {
        return event.getKey();
    }

    // Check whether this room is booked or not.
    public boolean isBooked() {return booked;}

    // Get the useTime of this room if an event happens in this room, else return null.
    public Calendar getUseTime() {
        return useTime;
    }

    // If this room is not booked, change the state to Book, set the event and useTime, and return True. Else, return
    // false.
    public boolean Book(Integer id, Integer duration, Calendar time) {
        if(!booked) {
            booked = true;
            event = new Pair(id, duration);
            useTime = time;
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
        if(MaxCapacity == getCurrentCapacity()) {
            return false;}
        for (String name: ListOfAttendees) {
            if(name.equals(username)){
                return false;}
        }
        ListOfAttendees.add(username);
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
                return true;
            }
        }
            return false;
        }

    @Override
    public String toString() {
        if(booked) {
            double curr = getCurrentCapacity();
            Integer duration = event.getValue();
            return "This is Room" + id + " with a maximum capacity of " + MaxCapacity + " and a current capacity of " +
                    curr + "." + "It will be used started from " + useTime + " and last for "  + duration + "minutes";
        }
        return "This is Room" + id + " with a maximum capacity of " + MaxCapacity +
                ". It is currently available for booking";
    }
}
