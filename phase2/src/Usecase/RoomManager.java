package Usecase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import Entity.*;

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
     * @param MaxCapacity: the maximum capacity of the room.
     * @return the id of the room.
     */
    public int createRoom(int MaxCapacity, ArrayList<String> constraints){
        Room room = new Room(allRooms.size() + 1, MaxCapacity, constraints);
        allRooms.add(room);
        return room.getId();
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
    public ArrayList<Integer> seeSchedule(int id, Date date){
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
    public void book(Integer roomId, Integer eventId, Date date){
        getRoom(roomId).getSchedule(date).add(eventId);
    }

    /**
     * Cancel an event scheduled in the room, return true if this event is successfully removed; else, return false.
     * @param roomId: the unique id of the room.
     * @param eventId: the unique id of the event that is going to be cancelled.
     * @param date: the date the event happen.
     */
    public void cancel(Integer roomId, Integer eventId, Date date){
        getRoom(roomId).getSchedule(date).remove(eventId);
    }

    /**
     * Get the constraints of the room.
     * @param roomId: the id of the room.
     * @return the arraylist of string representations of constraints of the room.
     */
    public ArrayList<String> seeConstraints(int roomId) {
        return getRoom(roomId).getConstraints();
    }

    /**
     * Add a new feature to the room.
     * @param roomId: the id of the room.
     * @param newFeature: the string representation of the new feature.
     * @return whether the new feature is successfully added.
     */
    public boolean addNewFeature(int roomId, String newFeature) {
        if(getRoom(roomId).getConstraints().contains(newFeature)) {
            return false;
        }
        getRoom(roomId).getConstraints().add(newFeature);
        return true;
    }

    /**
     * Remove a feature from the room.
     * @param roomId: the id of the room.
     * @param feature: the string representation of the feature.
     * @return whether the new feature is successfully removed.
     */
    public boolean removeFeature(int roomId, String feature) {
        return getRoom(roomId).getConstraints().remove(feature);
    }

}

