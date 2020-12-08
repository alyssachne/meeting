package Usecase;

import java.io.Serializable;
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
     */
    public int createRoom(int MaxCapacity, List<String> constraints){
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
     */
    public Integer getMaxCapacity(int id){
        return getRoom(id).getMaxCapacity();
    }

//    /**
//     * Return a list of integers which represent the available time of this room.
//     * @param roomId: the unique id of the room.
//     */
//    public ArrayList<Integer> availableTime(int roomId){
//        Room room = getRoom(roomId);
//        return room.getAvailableTime();
//    }

    /**
     * Printout all the rooms that suit the requirement of the event and the available time of each room.
     */
    public void suggestedRooms(int maxCapacity, List<String> constraints){
        for (Room room:allRooms){
            if (room.getMaxCapacity() >= maxCapacity && room.getConstraints().containsAll(constraints)) {
                System.out.println(room.getId());
//                for (Integer time : availableTime(room.getId())){
//                    System.out.println(time);
//                }
            }
        }
    }

    /**
     * Book the room for a certain event at a certain time, return true if there is no time conflict;
     * else, return false.
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

    public List<String> seeConstraints(int roomId) {
        return getRoom(roomId).getConstraints();
    }


    public boolean addNewFeature(int roomId, String newFeature) {
        if(getRoom(roomId).getConstraints().contains(newFeature)) {
            return false;
        }
        getRoom(roomId).getConstraints().add(newFeature);
        return true;
    }

    public boolean removeFeature(int roomId, String feature) {
        return getRoom(roomId).getConstraints().remove(feature);
    }






//    public boolean addTechnology(int roomId, String new_technology, Integer number) {
//        if (getRoom(roomId).getTechnology().containsKey(new_technology)) {
//            return false;
//        }
//        getRoom(roomId).getTechnology().put(new_technology, number);
//        return true;
//    }
////
//    public boolean changeTechnology(int roomId, String technology, Integer new_number) {
//        if (! getRoom(roomId).getTechnology().containsKey(technology)) {
//            return false;
//        }
//        getRoom(roomId).getTechnology().replace(technology, new_number);
//        return true;
//    }
//
//    public boolean removeTechnology(int roomId, String technology) {
//        if (! getRoom(roomId).getTechnology().containsKey(technology)) {
//            return false;
//        }
//        getRoom(roomId).getTechnology().remove(technology);
//        return true;
//    }
//
//    public Integer checkTechnology(int roomId, String technology) {
//        if (! getRoom(roomId).getTechnology().containsKey(technology)) {
//            return 0;
//        }
//        return getRoom(roomId).getTechnology().get(technology);
//    }
//
//    public HashMap<String, Integer> seeTechnology(int roomId) {
//        return getRoom(roomId).getTechnology();
//    }

}

