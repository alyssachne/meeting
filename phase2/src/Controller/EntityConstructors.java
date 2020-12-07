package Controller;

import Entity.Attendee;
import Usecase.*;

import javax.management.remote.rmi._RMIConnection_Stub;
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
    public static void createSpeaker(String name, String username, String password, SpeakerAct sa, AttendeeAct aa,
                                     OrganizerAct oa){
        // check if the username is taken by any user
        if(oa.checkUsernameTaken(username) && aa.checkUsernameTaken(username) &&
                !sa.createUser(name,username,password)){
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
    public static void createAttendee(String name, String username, String password, AttendeeAct aa, SpeakerAct sa,
                                      OrganizerAct oa){
        if(oa.checkUsernameTaken(username) && sa.checkUsernameTaken(username) &&
                !aa.createUser(name,username,password)) {
            System.out.println("This username has already been taken, please choose a new username.");
        }
    }

    /**
     * Create a new event with given username of the speaker, the Id of the event, the title of the event, the start
     * time of the event, and the Id of the room.
     * @param speaker The username of the speaker who is going to give the event.
     * @param eventId The unique Id of the event.
     * @param title The title of the event.
     * @param time The start time of the event.
     * @param roomId The unique Id of the room where this event takes place.
     */
    public static boolean createEvent(List<String> speaker, int eventId, String title, int time, int roomId,
                                      int duration, int maxCapacity, String eventAccess, List<String> constraints,
                                      RoomManager rm, SpeakerAct sa, EventManager em){
//        Set<Integer> temp = new HashSet<>();
//        List<Integer> spTime = new ArrayList<>();
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
                if (!sa.availableTime(sp).contains(i) | !rm.getRoom(roomId).getAvailableTime().contains(time) |
                !rm.getRoom(roomId).getConstraints().containsAll(constraints)) {
                    return false;
                }
            }
        }
        em.createEvent(eventId,title,time,roomId,speaker, duration, maxCapacity, eventAccess, constraints);
        // add the event to the speakers' given list and room's list.
        for(int i=0; i <= duration - 1; i++) {
            for(String s: speaker) {
                sa.giveEvent(s,eventId,time+i);
            }
            rm.book(roomId,eventId,time+i);
        }
        return true;
    }

}
