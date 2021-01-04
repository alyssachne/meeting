package Usecase;

import Entity.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use case class for the {@link Room} method
 *
 * <p>
 *     This class provides the essential functionalities associates with {@link Room} class, such
 *     as creating a Room object and set relevant instance variables. It also provides services to
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
     * @param MaxCapacity : the maximum capacity of the room.
     */
    public void createRoom(int MaxCapacity, ArrayList<String> constraints){
        Room room = new Room(allRooms.get(allRooms.size()-1).getId() + 1, MaxCapacity, constraints);
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
     * @return the max capacity of the room.
     */
    public Integer getMaxCapacity(int id){
        return getRoom(id).getMaxCapacity();
    }

    /**
     * Return the schedule of this room on the given date.
     * @param id: the unique id of the room.
     */
    public ArrayList<Integer> seeSchedule(int id, String date){
        return getRoom(id).getSchedule(date);
    }

    /**
     * Get the ids of all the rooms that suit the requirements of the event.
     * @param maxCapacity: the max capacity of the room.
     * @param constraints: the string representations of the constraints of the room.
     * @return the arraylist of ids of all the rooms that suit the requirements of the event.
     */
    public ArrayList<Integer> suggestedRooms(int maxCapacity, List<String> constraints){
        ArrayList<Integer> acc = new ArrayList<>();
        for (Room room:allRooms){
            if (room.getMaxCapacity() >= maxCapacity && room.getConstraints().containsAll(constraints)) {
                acc.add(room.getId());
            }
        }
        return acc;
    }

    /**
     * Book the room for a certain event at a certain time.
     * @param roomId: the unique id of the room.
     * @param eventId: the unique id of the event happen in this room.
     * @param date: the date the event happen.
     */
    public void book(Integer roomId, Integer eventId, String date){
        getRoom(roomId).getSchedule(date).add(eventId);
    }

    /**
     * Cancel an event scheduled in the room, return true if this event is successfully removed; else, return false.
     * @param roomId: the unique id of the room.
     * @param eventId: the unique id of the event that is going to be cancelled.
     * @param date: the date the event happen.
     */
    public void cancel(Integer roomId, Integer eventId, String date){
        getRoom(roomId).getSchedule(date).remove(eventId);
    }

}

