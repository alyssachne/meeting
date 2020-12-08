package Controller;

import Usecase.*;

//import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.*;

public class EntityConstructors {

    /**
     * Create a new room with given room Id and maximum capacity.
     * @param capacity The maximum capacity of the room.
     */
    public static void createRoom(int capacity, ArrayList<String> constraints, RoomManager rm){
        rm.createRoom(capacity,constraints);
    }
//
//    /**
//     * If the username is not taken by other user, allow the organizer to create this speaker account and return true;
//     * else, return false.
//     * @param name The name of the speaker.
//     * @param username The username of the speaker.
//     * @param password The password of the speaker.
//     */
//    public static void createSpeaker(String name, String username, String password, SpeakerAct sa, AttendeeAct aa,
//                                     OrganizerAct oa, MessageManager mm){
//        // check if the username is taken by any user
//        if(oa.checkUsernameTaken(username) && aa.checkUsernameTaken(username) &&
//                !sa.createUser(name,username,password)){
//            System.out.println("This username has already been taken, please choose a new username.");
//        } else {
//            sa.createUser(name,username,password);
//            mm.createMessageBox(username);
//        }
//    }

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
    public static boolean createEvent(ArrayList<String> speaker, String title, Date date, int roomId,
                                      int duration, String eventAccess, ArrayList<String> constraints,
                                      RoomManager rm, ActFactory af, EventFactory ef, CalendarManager cm){
//        Set<Integer> temp = new HashSet<>();
//        ArrayList<Integer> spTime = new ArrayList<>();
//        // collect all available times for each speaker
//        for(String s: speaker) {
//            temp.addAll(sa.availableTime(s));
//        }
//        // find out the time that all speakers are free.
//        for(Integer i: temp) {
//            if (Collections.frequency(temp,i) == speaker.size()) {
//                spTime.add(i);
//            }
//        }
//        ArrayList<Integer> roomTime = rm.availableTime(roomId);
//        for(int i=0; i <= duration - 1; i++) {
//            if (!spTime.contains(time + i) | !roomTime.contains(time + i)) {
//                return false;
//            }
//        }

        // check if all speakers are free from the beginning of the event to the end of the event
        // if the room is free from the beginning of the event to the end of the event/
        // if the room satisfies all constraints the event need
        for(String sp: speaker) {
            for(int i=0; i <= duration - 1; i++) {
                if (!cm.getAvailable(date, af.getEvents(sp)).contains(i) |
                        !cm.getAvailable(date, rm.getRoom(roomId).getSchedule(date)).contains(i) |
                !rm.getRoom(roomId).getConstraints().containsAll(constraints)) {
                    return false;
                }
            }
        }
        int eventId = ef.createEvent(title,date,roomId,speaker, duration, eventAccess, constraints);
        // add the event to the speakers' given ArrayList and room's ArrayList.
        for(int i=0; i <= duration - 1; i++) {
            for(String s: speaker) {
                af.giveEvent(s,eventId,date);
            }
            rm.book(roomId,eventId,date);
            cm.newEvent(date, i, eventId);
        }
        return true;
    }

}