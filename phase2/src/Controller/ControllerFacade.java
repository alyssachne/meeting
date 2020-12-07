package Controller;

import Usecase.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ControllerFacade implements Serializable {

    protected RoomManager rm = new RoomManager();
    protected PartyManager pm = new PartyManager();
    protected TalkManager tm = new TalkManager();
    protected DiscussionManager dm = new DiscussionManager();
    protected RequestManager reqm = new RequestManager();
    protected MessageManager mm = new MessageManager();
    protected SpeakerAct sa = new SpeakerAct();
    protected OrganizerAct oa = new OrganizerAct();
    protected AttendeeAct aa = new AttendeeAct();
    protected CalendarManager cm = new CalendarManager();
    protected String username;
    protected String type;

    /**
     * Class constructor: create a default Organizer account whose name, username, and password are all "admin".
     */
    public ControllerFacade(){
        oa.createUser("admin","admin","admin");
        mm.createMessageBox("admin");
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
    public void createRoom(int id,int capacity, List<String> constraints){
        EntityConstructors.createRoom(id,capacity,constraints,rm);
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
        EntityConstructors.createSpeaker(name, username , password, sa, aa, oa, mm);
    }

    /**
     * If the username is not taken by other attendee, allow the attendee to create the account and return true;
     * else, return false.
     * @param name The name of the attendee.
     * @param username The username of the attendee.
     * @param password The password of the attendee.
     */
    public void createAttendee(String name, String username, String password){
        EntityConstructors.createAttendee(name,username,password, aa, sa, oa,mm);
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
    public void createEvent(List<String> username, int eventId, String title, Date date, int time, int roomId, int maxCapacity,
                               int duration, String eventAccess, List<String> constraints){
        if(username.size() == 0) {
            EntityConstructors.createEvent(username,eventId,title,date,time,roomId,duration,maxCapacity,eventAccess,
                    constraints, this.rm,this.sa,this.pm, cm);
        } else if(username.size() == 1) {
            EntityConstructors.createEvent(username,eventId,title,date,time,roomId,duration,maxCapacity,eventAccess,
                    constraints, this.rm,this.sa,this.tm,cm);
        } else {
            EntityConstructors.createEvent(username,eventId,title,date,time,roomId,duration,maxCapacity,eventAccess,
                    constraints, this.rm,this.sa,this.dm,cm);
        }
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerAvailable(Date date){
        Getter.speakerAvailable(this.sa, date, cm);
    }

    /**
     * printout all rooms and their available times.
     */
    public void roomList(int maxCapacity, List<String> constraints){
        Getter.roomList(this.rm, maxCapacity, constraints);
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public void speakerSchedule(){
        Getter.speakerSchedule(this.sa,this.username,this.dm,this.tm,this.pm);
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public void attendeeSchedule(){
        Getter.attendeeSchedule(this.aa,this.username,this.dm,this.tm,this.pm);
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public void getAvailableEvent(String sort){
        Getter.getAvailableEvent(this.rm,this.username,sort,this.dm,this.tm,this.pm);
    }

    public void getEnrollmentStatistics(){
        Getter.enrollmentStatistics(this.dm,this.tm,this.pm);
    }

    public void getTopFiveLists(){
        Getter.getTopFiveEvents(this.dm,this.tm,this.pm);
    }

    public void getAppTraffic(){
        Getter.getAppTraffic(this.dm,this.tm,this.pm);
    }
    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public void signUp(int eventId){
        EventDealer.signUp(eventId,this.aa,checkEventType(eventId),this.username);
    }



    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public void cancelSpot(int eventId){
        EventDealer.cancelSpot(eventId,this.aa,checkEventType(eventId),this.username);
    }

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public void groupMessageTo(String message,String userType){
        MessageDealer.groupMessageTo(message,userType,this.sa,this.aa,this.mm,this.username);
    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public void eventMessage_Attendee(String message, Integer eventId){
        MessageDealer.eventMessage_Attendee(message,eventId,checkEventType(eventId),this.mm,this.username);
    }

    /**
     * Allow the user to send messages to a specific user.
     * @param receiver The username of the user who is going to receive this message.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker, organizer, or Attendee.
     * @param message The message this user wants to send.
     */
    public void privateMessageTo(String receiver, String userType, String message){
        MessageDealer.privateMessageTo(receiver,message,this.mm,this.username);
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public void checkContacts(){
        UserCheckers.checkContacts(this.mm,this.username);
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public void getMessage(String sender){
        MessageDealer.getMessage(sender,this.mm,this.username);
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public void checkAudiences(Integer eventId){
        UserCheckers.checkAudiences(eventId,checkEventType(eventId));
    }

    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public void checkSpeakers() {
    UserCheckers.checkSpeakers(this.aa, this.username, this.dm,this.tm,this.pm);}

    public void checkAllRequest() {
        RequestDealer.checkAllRequest(reqm);
    }

    public void tagRequest(int id) {
        RequestDealer.tagRequest(id, reqm);
    }

    public void updateRoom(List<String> newFeatures,int roomId) {RoomDealer.updateRoom(newFeatures, rm, roomId);}

    public void cleanRoom(List<String> oldFeatures, int roomId) {RoomDealer.cleanRoom(oldFeatures, rm, roomId);}

    public void markAsUnread(String sender, String message) {
        MessageDealer.markAsUnread(username,mm, message, sender);
    }

    public void deleteMessage(String sender, String message, String box) {
        MessageDealer.deleteMessage(username,mm, message, sender, box);
    }

    public void archiveMessage(String sender, String message) {
        MessageDealer.archiveMessage(username,mm, message, sender);
    }

    public void cancelEvent(int eventId, Date date) {
        EventDealer.cancelEvent(eventId, date, checkEventType(eventId), sa, aa, rm, cm);
    }

    public void changeAccess(int eventId,String access){EventDealer.changeAccess(eventId,checkEventType(eventId), access);}

    private EventManager checkEventType(int id) {
        if(pm.containEvent(id)) {
            return pm;
        } else if(tm.containEvent(id)) {
            return tm;
        } else {
            return dm;
        }
    }
}
