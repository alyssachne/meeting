package Controller;

import Usecase.*;

//import javax.management.remote.rmi._RMIConnection_Stub;
import java.io.Serializable;
import java.util.*;

public class EntityConstructors implements Serializable {

    /**
     * Create a new room with given room Id and maximum capacity.
     * @param capacity The maximum capacity of the room.
     */
    public static void createRoom(int capacity, ArrayList<String> constraints, RoomManager rm){
        rm.createRoom(capacity,constraints);
    }

    /**
     * If the username is not taken by other attendee, allow the attendee to create the account and return true;
     * else, return false.
     * @param name The name of the attendee.
     * @param username The username of the attendee.
     * @param password The password of the attendee.
     */
    public static void createUser(String name, String username, String password, ActFactory af, MessageManager mm,
                                  String userType) {
        if (af.checkUsernameTaken(username)) {
            System.out.println("This username has already been taken, please choose a new username.");
        } else {
            af.createUser(name, username, password, userType);
            mm.createMessageBox(username);
        }
    }

    /**
     * Create a new event with given username of the speaker, the Id of the event, the title of the event, the start
     * time of the event, and the Id of the room.
     * @param speaker The username of the speaker who is going to give the event.
     * @param title The title of the event.
     * @param date The start time of the event.
     * @param roomId The unique Id of the room where this event takes place.
     */
    public static boolean createEvent(ArrayList<String> speaker, String title, Date date, int time, int roomId,
                                      int duration, String eventAccess, ArrayList<String> constraints,
                                      RoomManager rm, ActFactory af, EventFactory ef, CalendarManager cm){
        for(int i=time; i <= time + duration - 1; i++) {
            for(String sp: speaker) {
                if (!cm.getAvailable(date, af.givenEvents(sp,date)).contains(i)){
                    return false;
                }
            }
            if(!cm.getAvailable(date, rm.getRoom(roomId).getSchedule(date)).contains(i) |
                !rm.getRoom(roomId).getConstraints().containsAll(constraints)) {
                    return false;
                }
            }

        Date copy = (Date) date.clone();
        date.setHours(time);
        int eventId = ef.createEvent(title,date,roomId,speaker, duration, eventAccess, constraints);
        // add the event to the speakers' given ArrayList and room's ArrayList.
        for(int i=time; i <= time + duration - 1; i++) {
            for(String s: speaker) {
                af.giveEvent(s,eventId,copy);
            }
            rm.book(roomId,eventId,copy);
            cm.newEvent(copy, i, eventId);
        }
        return true;
    }

    /**
     * A method used to validate if a given user is a valid speaker
     * @param speaker, The User name of the user to be validated.
     * @param af, the class we used to manage all the users.
     * @return a boolean value indicating if the user is a valid speaker.
     */
    public static boolean validSpeaker(String speaker, ActFactory af){
        return af.speakerList().contains(speaker);
    }

    /**
     * A method used to validate if a given room exist in the system.
     * @param roomId, The id of the rooms to be validated.
     * @param rm, the class we used to manage all the rooms.
     * @return a boolean value indicating if the room is a valid room.
     */
    public static boolean validRoom(int roomId, RoomManager rm){
        return rm.allRooms.contains(rm.getRoom((roomId)));
    }

    /**
     * A method used to validate if at least one speaker exist in the system.
     * @return a boolean value indicating if there is at least one speaker.
     */
    public static boolean hasSpeakers(ActFactory af) {
        return !af.speakerList().isEmpty();
    }

    /**
     * A method used to validate if at least one room exist in the system.
     * @return a boolean value indicating if there is at least one room.
     */
    public static boolean hasRoom(RoomManager rm) {
        return !rm.allRooms.isEmpty();
    }

}
