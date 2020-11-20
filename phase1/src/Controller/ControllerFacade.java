package Controller;

import Entity.*;
import Usecase.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ControllerFacade implements Serializable {

    RoomManager rm = new RoomManager();
    EventManager em = new EventManager();
    SpeakerAct sa = new SpeakerAct();
    OrganizerAct oa = new OrganizerAct();
    AttendeeAct aa = new AttendeeAct();
    String username;
    String type;

    /**
     * Class constructor: create a default Organizer account whose name, username, and password are all "admin".
     */
    public ControllerFacade(){
        oa.createUser("admin","admin","admin");
    }

    /**
     * If the username, password, and userType matches, allow this user to login by returning true; else, return false.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param userType The type of the user.
     */
    public boolean login(String username, String password, String userType){
        if (userType.equalsIgnoreCase("Organizer")){
            //go to OrganizerAct to check
            if (oa.login(username,password)){
                this.username = username;
                type = "Organizer";
                return true;
            }
        }else if(userType.equalsIgnoreCase("Speaker")){
            //go to SpeakerAct to check
            if(sa.login(username,password)){
                this.username = username;
                type = "Speaker";
                return true;
            }
        }else if(userType.equalsIgnoreCase("Attendee")){
            //go to AttendeeAct to check
            if (aa.login(username,password)){
                this.username = username;
                type = "Attendee";
                return true;
            }

        }
        return false;
    }

    /**
     * Logout of the account by setting the username and type to null.
     */
    public void logout(){
        username = null;
        type = null;
    }

    /**
     * Create a new room with given room Id and maximum capacity.
     * @param id The unique Id of the room.
     * @param capacity The maximum capacity of the room.
     */
    public void createRoom(int id,int capacity){
        rm.createRoom(id,capacity);
    }

    /**
     * If the username is not taken by other user, allow the organizer to create this speaker account and return true;
     * else, return false.
     * @param name The name of the speaker.
     * @param username The username of the speaker.
     * @param password The password of the speaker.
     */
    public void createSpeaker(String name, String username, String password){
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
    public void createAttendee(String name, String username, String password){
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
    public boolean createEvent(String username, int eventId, String title, int time, int roomId){
        ArrayList<Integer> spTime = sa.availableTime(username);
        ArrayList<Integer> roomTime = rm.availableTime(roomId);
        if (spTime.contains(time) && roomTime.contains(time)) {
            em.createEvent(eventId,title,time,roomId,username);
            sa.giveEvent(username,eventId,time);
            rm.book(roomId,eventId,time);
            return true;
        }
        return false;
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerList(){
        sa.speakerList();
    }

    /**
     * printout all rooms and their available times.
     */
    public void roomList(){
        rm.roomList();
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public void speakerSchedule(){
        for (int id : sa.eventList(username)){
            //sa.eventList(username) is arraylist of eventIds
            System.out.println(em.getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public void attendeeSchedule(){
        for (int id : aa.getEvents(username)){
            System.out.println(em.getEvent(id).toString());
        }
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public void getAvailableEvent(){
        //events that are not full
        for (Event event: em.allEvents){
           if ((!event.getAttendees().contains(username))&&
                   rm.getMaxCapacity(event.getRoom())>event.getAttendees().size()){
               //check if the attendee has signed up the event or not and if the event reaches its room's maxCapacity
               System.out.println(em.getEvent(event.getId()).toString());
           }
        }
    }

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public void signUp(int eventId){
        aa.signUp(username,eventId);
        em.addAttendee(username,eventId);
    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public void cancelSpot(int eventId){
        aa.cancelSpot(username,eventId);
        em.cancelSpot(username,eventId);
    }

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public void groupMessageTo(String message,String userType){
        if (userType.equals("Speaker")){
            for(String username: sa.speakerMap.keySet()){
                sa.addMessage(username,this.username,message);
            }
        }else if (userType.equals("Attendee")){
            for(String username: aa.attendeeMap.keySet()){
                aa.addMessage(username,this.username,message);
            }
        }

    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public void eventMessage_Attendee(String message, Integer eventId){
        for (String username: em.getEvent(eventId).getAttendees()) {
            aa.addMessage(username,this.username,message);
        }
    }

    /**
     * Allow the user to send messages to a specific user.
     * @param receiver The username of the user who is going to receive this message.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker, organizer, or Attendee.
     * @param message The message this user wants to send.
     */
    public void privateMessageTo(String receiver, String userType, String message){
        if (userType.equals("Speaker")){
            sa.addMessage(receiver,username,message);
        }else if (userType.equals("Attendee")){
            aa.addMessage(receiver,username,message);
        }
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public void checkContacts(){
        if (type.equals("Organizer")){
            for(String name : oa.getContacts(username)){
                System.out.println(name);
            }
        }else if (type.equals("Speaker")){
            for(String name : sa.getContacts(username)){
                System.out.println(name);
            }
        }else if (type.equals("Attendee")){
            for(String name : aa.getContacts(username)){
                System.out.println(name);
            }
        }
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public void getMessage(String sender){
        if (type.equals("Organizer")){
            for (String message : oa.getMessage(username,sender)){
                System.out.println(message);
            }
        }else if (type.equals("Speaker")){
            for (String message : sa.getMessage(username,sender)){
                System.out.println(message);
            }
        }else if (type.equals("Attendee")){
            for (String message : aa.getMessage(username,sender)){
                System.out.println(message);
            }
        }
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public void checkAudiences(Integer eventId){
        for(String name : em.getEvent(eventId).getAttendees()){
            System.out.println(name);
        }
    }

    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public void checkSpeakers() {
        for(Integer eventId: aa.getEvents(username)) {
            System.out.println(em.getEvent(eventId).getSpeaker());
        }
    }
}
