package Controller;

import Entity.*;
import Usecase.*;

import javax.rmi.PortableRemoteObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControllerFacade implements Serializable {

    protected RoomManager rm = new RoomManager();
    protected PartyManager pm = new PartyManager();
    protected TalkManager tm = new TalkManager();
    protected DiscussionManager dm = new DiscussionManager();
    protected SpeakerAct sa = new SpeakerAct();
    protected OrganizerAct oa = new OrganizerAct();
    protected AttendeeAct aa = new AttendeeAct();
    protected String username;
    protected String type;

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
        EntityConstructors.createRoom(id,capacity,rm);
    }

    /**
     * If the username is not taken by other user, allow the organizer to create this speaker account and return true;
     * else, return false.
     * @param name The name of the speaker.
     * @param username The username of the speaker.
     * @param password The password of the speaker.
     *
     */
    public void createSpeaker(String name, String username, String password){
        EntityConstructors.createSpeaker(name, username , password, this.sa, aa, oa);
    }

    /**
     * If the username is not taken by other attendee, allow the attendee to create the account and return true;
     * else, return false.
     * @param name The name of the attendee.
     * @param username The username of the attendee.
     * @param password The password of the attendee.
     */
    public void createAttendee(String name, String username, String password){
        EntityConstructors.createAttendee(name,username,password, this.aa, sa, oa);
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
    public boolean createEvent(List<String> username, int eventId, String title, int time, int roomId, int maxCapacity){
        if(username.size() == 0) {
            return EntityConstructors.createEvent(username,eventId,title,time,roomId,maxCapacity,this.rm,this.sa,this.pm);
        } else if(username.size() == 1) {
            return EntityConstructors.createEvent(username,eventId,title,time,roomId,maxCapacity,this.rm,this.sa,this.tm);
        } else {
            return EntityConstructors.createEvent(username, eventId, title, time, roomId, maxCapacity, this.rm, this.sa, this.dm);
        }
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerList(){
        ScheduleGetter.speakerList(this.sa);
    }

    /**
     * printout all rooms and their available times.
     */
    public void roomList(){
        ScheduleGetter.roomList(this.rm);
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public void speakerSchedule(){
        ScheduleGetter.speakerSchedule(this.sa,this.username,this.dm,this.tm,this.pm);
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public void attendeeSchedule(){
        ScheduleGetter.attendeeSchedule(this.aa,this.username,this.dm,this.tm,this.pm);
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public void getAvailableEvent(){
        ScheduleGetter.getAvailableEvent(this.rm,this.username,this.dm,this.tm,this.pm);
    }

    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public void signUp(int eventId){
        EventSigner.signUp(eventId,this.aa,checkEventType(eventId, dm, tm, pm),this.username);
    }

    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public void cancelSpot(int eventId){
        EventSigner.cancelSpot(eventId,this.aa,checkEventType(eventId, dm, tm, pm),this.username);
    }

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public void groupMessageTo(String message,String userType){
        Messagers.groupMessageTo(message,userType,this.sa,this.aa,this.username);
    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public void eventMessage_Attendee(String message, Integer eventId){
        Messagers.eventMessage_Attendee(message,eventId,this.aa,checkEventType(eventId, dm, tm, pm),this.username);
    }

    /**
     * Allow the user to send messages to a specific user.
     * @param receiver The username of the user who is going to receive this message.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker, organizer, or Attendee.
     * @param message The message this user wants to send.
     */
    public void privateMessageTo(String receiver, String userType, String message){
        Messagers.privateMessageTo(receiver,userType,message,this.sa,this.aa,this.username);
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public void checkContacts(){
        UserCheckers.checkContacts(type,this.oa,this.sa,this.aa,this.username);
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public void getMessage(String sender){
        Messagers.getMessage(sender,this.sa,this.aa,this.oa,this.type,this.username);
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public void checkAudiences(Integer eventId){
        UserCheckers.checkAudiences(eventId,checkEventType(eventId, dm, tm, pm));
    }

    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public void checkSpeakers() {
    UserCheckers.checkSpeakers(this.aa, this.username, this.dm,this.tm,this.pm);}

    public static EventManager checkEventType(int id, DiscussionManager dm, TalkManager tm,PartyManager pm) {
        if(pm.containEvent(id)) {
            return pm;
        } else if(tm.containEvent(id)) {
            return tm;
        } else {
            return dm;
        }
    }
}
