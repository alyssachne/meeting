package Controller;

import Controller.Sorter.SorterStrategy;
import Entity.User;
import Usecase.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

public class ControllerFacade implements Serializable {

    protected RoomManager rm = new RoomManager();
    protected EventFactory ef = new EventFactory();
    protected RequestManager reqm = new RequestManager();
    protected MessageManager mm = new MessageManager();
    protected ActFactory af = new ActFactory();
    protected CalendarManager cm = new CalendarManager();
    protected String username;
//    protected String type;

    /**
     * Class constructor: create a default Organizer account whose name, username, and password are all "admin".
     */
    public ControllerFacade(){
        af.createUser("admin","admin","admin","Organizer");
        mm.createMessageBox("admin");
    }

    /**
     * If the username, password, and userType matches, allow this user to login by returning true; else, return false.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public boolean login(String username, String password){
        if(af.login(username,password)){
            this.username = username;
            return true;
        }
        return false;
    }

    /**
     * Logout of the account by setting the username and type to null.
     */
    public void logout(){
        username = null;
//        type = null;
    }

    /**
     * Create a new room with given room Id and maximum capacity.
     * @param capacity The maximum capacity of the room.
     */
    public void createRoom(int capacity, ArrayList<String> constraints){
        EntityConstructors.createRoom(capacity,constraints,rm);
    }

    /**
     * If the username is not taken by other user, allow the organizer to create this user account and return true;
     * else, return false.
     * @param name The name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     *
     */
    public void createSpeaker(String name, String username, String password, String userType){
        EntityConstructors.createUser(name, username , password, af, mm, userType);
    }
//
//    /**
//     * If the username is not taken by other attendee, allow the attendee to create the account and return true;
//     * else, return false.
//     * @param name The name of the attendee.
//     * @param username The username of the attendee.
//     * @param password The password of the attendee.
//     */
//    public void createAttendee(String name, String username, String password){
//        EntityConstructors.createAttendee(name,username,password, aa, sa, oa,mm);
//    }

    /**
     * Create a new event with given username of the speaker, the Id of the event, the title of the event, the start
     * time of the event, and the Id of the room.
     * @param speakers The ArrayList of username of the speakers who are going to give the event.
     * @param title The title of the event.
     * @param date The start time of the event.
     * @param roomId The unique Id of the room where this event takes place.
     */
    public void createEvent(ArrayList<String> speakers, String title, Date date, int roomId, int duration, String eventAccess, ArrayList<String> constraints){
            EntityConstructors.createEvent(speakers,title,date,roomId,duration,eventAccess,constraints,this.rm,this.af,ef,cm);
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerAvailable(Date date){
        Getter.speakerAvailable(this.af, date, cm);
    }

    /**
     * printout all rooms and their available times.
     */
    public void roomList(int maxCapacity, ArrayList<String> constraints){
        Getter.roomList(this.rm, maxCapacity, constraints);
    }

    /**
     * printout all events this speaker is going to give. This schedule is only shown to the speaker.
     */
    public void speakerSchedule(){
        ScheduleGetter.speakerSchedule(this.af,this.username,ef);
    }

    /**
     * printout all events this attendee signed up for. This schedule is only shown to this attendee.
     */
    public void attendeeSchedule(SorterStrategy sort, Map<String, String> filter) throws ParseException {
        ScheduleGetter.attendeeSchedule(this.af,this.username,ef,sort,filter);
    }

    public void getAllSelectedEvents(SorterStrategy sort, Map<String, String> filter) throws ParseException {
        ScheduleGetter.getAllSelectedEvents(sort,filter, ef);
    }

    public void getLikedEvents(SorterStrategy sort, Map<String, String> filter) throws ParseException {
        ScheduleGetter.getLikedEvents(username, sort, filter, af, ef);
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public void getAvailableEvent(SorterStrategy sort){
        ScheduleGetter.getAvailableEvent(this.rm,this.username,sort,ef);
    }

    public void getEnrollmentStatistics(){
        Getter.enrollmentStatistics(ef,rm);
    }

    public void getTopFiveLists(){
        Getter.getTopFiveEvents(ef);
    }

    public void getAppTraffic(){
        Getter.getAppTraffic(ef,rm);
    }
    /**
     * Signup this attendee to the event.
     * @param eventId The Id of the event this attendee wants to sign up for.
     */
    public void signUp(int eventId){
        EventDealer.signUp(eventId,this.af,ef,this.username);
    }



    /**
     * Remove this attendee from the event.
     * @param eventId The Id of the event this attendee wants to be removed from.
     */
    public void cancelSpot(int eventId){
        EventDealer.cancelSpot(eventId,this.af,ef,this.username);
    }

    /**
     * Allow the organizer to send messages to all attendees or all speakers.
     * @param message The message this organizer wants to send.
     * @param userType The type of users this organizer wants to send to, i.e. Speaker or Attendee.
     */
    public void groupMessageTo(String message,String userType){
        MessageDealer.groupMessageTo(message,userType,af,this.mm,this.username);
    }

    /**
     * Allow the speaker to send messages to all attendees in certain event.
     * @param message The message this speaker wants to send.
     * @param eventId The Id of the certain event this speaker choose.
     */
    public void eventMessage_Attendee(String message, Integer eventId){
        MessageDealer.eventMessage_Attendee(message,eventId,ef,this.mm,this.username);
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
        UserPrinter.checkContacts(this.mm,this.username);
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
        UserPrinter.checkAudiences(eventId,ef);
    }

    /**
     * Printout all usernames of speakers who give the events this attendee signed up for.
     */
    public void checkSpeakers() {
    UserPrinter.checkSpeakers(this.af, this.username, ef);}

    public void checkAllRequest() {
        RequestDealer.checkAllRequest(reqm);
    }

    public void tagRequest(int id) {
        RequestDealer.tagRequest(id, reqm);
    }

    public void updateRoom(ArrayList<String> newFeatures,int roomId) {RoomDealer.updateRoom(newFeatures, rm, roomId);}

    public void cleanRoom(ArrayList<String> oldFeatures, int roomId) {RoomDealer.cleanRoom(oldFeatures, rm, roomId);}

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
        EventDealer.cancelEvent(eventId, date, ef, af, rm, cm);
    }

    public void changeEventAccess(int eventId,String access){EventDealer.changeEventAccess(eventId,ef, access);}

    public void madeRequest(String request) {
        RequestDealer.madeRequest(reqm, request,username);
    }

    public void likeEvent(int eventId) {
        EventDealer.likeEvent(eventId,af,username);
    }

    public void changeUserAccess(String access){
        UserPrinter.changeUserAccess(username,af,access);}
}