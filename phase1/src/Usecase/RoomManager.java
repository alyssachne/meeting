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

    public void createRoom(int id, int MaxCapacity){
        Room room = new Room(id, MaxCapacity);
    }

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
    // controller need to call both RoomManager and TalkManager to add or cancel the attendee.
    public boolean addAttendee(Room room) {
        if (room.full) {
            return false;
        }
        room.currentCapacity += 1;
        if(room.currentCapacity == room.MaxCapacity) {
            room.full = true;
        }
        return true;
    }

    public boolean removeAttendee(Room room) {
        if (room.currentCapacity == 0) {
            return false;
        }
        if (room.currentCapacity == room.MaxCapacity) {
            room.full = false;
        }
        room.currentCapacity -= 1;
        return true;
    }
}

