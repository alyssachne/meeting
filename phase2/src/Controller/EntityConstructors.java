package Controller;

import Usecase.*;

import java.util.*;

public class EntityConstructors {

    /**
     * Create a new room with given room Id and maximum capacity.
     * @param id The unique Id of the room.
     * @param capacity The maximum capacity of the room.
     */
    public static void createRoom(int id, int capacity, RoomManager rm){
        rm.createRoom(id,capacity);
    }

    /**
     * If the username is not taken by other user, allow the organizer to create this speaker account and return true;
     * else, return false.
     * @param name The name of the speaker.
     * @param username The username of the speaker.
     * @param password The password of the speaker.
     */
    public static void createSpeaker(String name, String username, String password, SpeakerAct sa){
        if(!sa.createUser(name,username,password)){
            System.out.println("This username has already been taken, please choose a new username.");
        }
    }

    /**
     * If the username is not taken by other attendee, allow the attendee to create the account and return true;
     * else, return false.
     * @param name The name of the attendee.
     * @param username The username of the attendee.
     * @param password The password of the attendee.
     */
    public static void createAttendee(String name, String username, String password, AttendeeAct aa){
        if(!aa.createUser(name,username,password)) {
            System.out.println("This username has already been taken, please choose a new username.");
        }
    }

    /**
     * Create a new event with given username of the speaker, the Id of the event, the title of the event, the start
     * time of the event, and the Id of the room.
     * @param username The username of the speaker who is going to give the event.
     * @param eventId The unique Id of the event.
     * @param title The title of the event.
     * @param time The start time of the event.
     * @param roomId The unique Id of the room where this event takes place.
     */
    public static boolean createEvent(String username, int eventId, String title, int time, int roomId, int maxCapacity,
                                      RoomManager rm, SpeakerAct sa, EventManager em){
        ArrayList<Integer> spTime = sa.availableTime(username);
        ArrayList<Integer> roomTime = rm.availableTime(roomId);
        if (spTime.contains(time) && roomTime.contains(time)) {
            List<String> speakers = new ArrayList<>();
            speakers.add(username);
            em.createEvent(eventId,title,time,roomId,speakers, maxCapacity);
            sa.giveEvent(username,eventId,time);
            rm.book(roomId,eventId,time);
            return true;
        }
        return false;
    }

}
