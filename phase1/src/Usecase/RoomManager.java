package Usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Entity.*;

public class RoomManager implements Serializable {

    public ArrayList<Room> allRooms;

    /**
     * Creates an empty room manager.
     */
    public RoomManager() {
        allRooms = new ArrayList<>();
    }

    /**
     * Create a new room.
     * @param id: the id of the room.
     * @param MaxCapacity: the maximum capacity of the room.
     */
    public void createRoom(int id, int MaxCapacity){
        Room room = new Room(id, MaxCapacity);
        allRooms.add(room);
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

    public Integer getMaxCapacity(int id){
        return getRoom(id).getMaxCapacity();
    }
    public ArrayList<Integer> availableTime(int roomId){
        Room room = getRoom(roomId);
        return room.getAvailableTime();
    }

    public void roomList(){
        for (Room room:allRooms){
            System.out.println(room.getId());
            for (Integer time : availableTime(room.getId())){
                System.out.println(time);
            }
        }
    }
    public boolean book(Integer roomId, Integer eventId, Integer time){
        if(!getRoom(roomId).isBooked(time)) {
            getRoom(roomId).getSchedule().put(time, eventId);
            return true;
        }
        return false;
    }

    public boolean cancel(Integer roomId, Integer eventId){
        HashMap<Integer, Integer> schedule = getRoom(roomId).getSchedule();
        Set<Integer> temp = schedule.keySet();
        for(Integer time: temp) {
            if(schedule.get(time).equals(eventId)) {
                schedule.replace(time, null);
                return true;
            }
        }
        return false;
    }

    //    /**
//     * Add one attendee to this room, increase the current capacity by 1, and return true if the room is not full. Else,
//     * return false.,
//     * @param room: the room the attendee is going to be add in.
//     * @param attendee: the attendee who is going to be added into this room.
//     */
//    public boolean addAttendee(Room room, Attendee attendee, Event event) {
//        String username = attendee.getUsername();
//        Integer time = event.time;
//        return room.addAttendee(username, time);
//    }

//    /**
//     * Remove one attendee to this room, decrease the current capacity by 1, and return true if the room is not empty.
//     * Else, return false.
//     * @param room: the room the attendee is going to be remove from.
//     * @param attendee: the attendee who is going to be removed from this room.
//     */
//    public boolean removeAttendee(Room room, Attendee attendee, Event event) {
//        String username = attendee.getUsername();
//        Integer time = event.time;
//        return room.removeAttendee(username, time);
//    }
}

