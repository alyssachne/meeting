package Usecase;

import java.util.ArrayList;
import Entity.*;

public class RoomManager {

    public ArrayList<Room> allRooms;

    /**
     * Creates an empty room manager.
     */
    public RoomManager() {
        allRooms = new ArrayList<>();
    }

    /**
     * Adds a given room to this room manager.
     * @param r: the room being added to the ArrayList.
     */
    public void addRoom(Room r) {
        allRooms.add(r);
    }

    /**
     * Create a new room.
     * @param id: the id of the room.
     * @param MaxCapacity: the maximum capacity of the room.
     */
    public void createRoom(int id, int MaxCapacity){
        Room room = new Room(id, MaxCapacity);
    }

    /**
     * Return the room of the corresponding id, raise error if not found.
     * @param id: the id of the room.
     */
    public Room getRoom(int id) {
        try {
            for (Room r : allRooms) {
                if (r.getId() == id) {
                    return r;
                }
            }
        }
        catch(Exception e) {
            System.out.println("This room does not exist.");
        }
        return null;
    }

    /**
     * Add one attendee to this room, increase the current capacity by 1, and return true if the room is not full. Else,
     * return false.,
     * @param room: the room the attendee is going to be add in.
     * @param attendee: the attendee who is going to be added into this room.
     */
    public boolean addAttendee(Room room, Attendee attendee, Event event) {
        String username = attendee.getUsername();
        Integer time = event.time;
        return room.addAttendee(username, time);
    }

    /**
     * Remove one attendee to this room, decrease the current capacity by 1, and return true if the room is not empty.
     * Else, return false.
     * @param room: the room the attendee is going to be remove from.
     * @param attendee: the attendee who is going to be removed from this room.
     */
    public boolean removeAttendee(Room room, Attendee attendee, Event event) {
        String username = attendee.getUsername();
        Integer time = event.time;
        return room.removeAttendee(username, time);
    }
}

