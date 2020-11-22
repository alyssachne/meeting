package Usecase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Entity.*;

/**
 * The usecase class for the {@link Room} method
 *
 * <p>
 *     This class provides the essential functionalities associates with {@link Room} class, such
 *     as creating a Room object and set relavent instance variables. It also provides services to
 *     look for these information inside an entity Room object.
 *</p>
 * */

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

    /**
     * Return the maximum capacity of this room.
     * @param id: the unique id of the room.
     */
    public Integer getMaxCapacity(int id){
        return getRoom(id).getMaxCapacity();
    }

    /**
     * Return a list of integers which represent the available time of this room.
     * @param roomId: the unique id of the room.
     */
    public ArrayList<Integer> availableTime(int roomId){
        Room room = getRoom(roomId);
        return room.getAvailableTime();
    }

    /**
     * Printout all the rooms and the available time of each room.
     */
    public void roomList(){
        for (Room room:allRooms){
            System.out.println(room.getId());
            for (Integer time : availableTime(room.getId())){
                System.out.println(time);
            }
        }
    }

    /**
     * Book the room for a certain event at a certain time, return true if there is no time conflict;
     * else, return false.
     * @param roomId: the unique id of the room.
     * @param eventId: the unique id of the event happen in this room.
     * @param time: the start time of the event.
     */
    public boolean book(Integer roomId, Integer eventId, Integer time){
        if(!getRoom(roomId).isBooked(time)) {
            getRoom(roomId).getSchedule().put(time, eventId);
            return true;
        }
        return false;
    }

    /**
     * Cancel an event scheduled in the room, return true if this event is successfully removed; else, return false.
     * @param roomId: the unique id of the room.
     * @param eventId: the unique id of the event that is going to be cancelled.
     */
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
}

