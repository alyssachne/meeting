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
}

