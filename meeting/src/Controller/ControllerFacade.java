package Controller;

import Usecase.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ControllerFacade implements Serializable {

    protected RoomManager rm = new RoomManager();
    protected EventFactory ef = new EventFactory();
    protected RequestManager reqm = new RequestManager();
    protected MessageManager mm = new MessageManager();
    protected ActFactory af = new ActFactory();
    protected CalendarManager cm = new CalendarManager();
    public String username;
    protected String type;

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
        if(af.login(username,password)) {
            this.username = username;
            type = af.getUser(username).typeGetter();
            return true;
        }
        return false;
    }

    /**
     * Get the user type
     */
    public String typeGetter(){
        return type;
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
    public void createUser(String name, String username, String password, String userType){
        EntityConstructors.createUser(name, username , password, af, mm, userType);
    }

    /**
     * Create a new event with given username of the speaker, the Id of the event, the title of the event, the start
     * time of the event, and the Id of the room.
     * @param speakers The ArrayList of username of the speakers who are going to give the event.
     * @param title The title of the event.
     * @param date The start time of the event.
     * @param roomId The unique Id of the room where this event takes place.
     */
    public boolean createEvent(ArrayList<String> speakers, String title, String date, int time, int roomId, int duration, String eventAccess, ArrayList<String> constraints){
            return EntityConstructors.createEvent(speakers,title,date,time,roomId,duration,eventAccess,constraints,this.rm,this.af,ef,cm);
    }

    /**
     * Check whether it has a speaker or not
     */
    public boolean hasSpeaker(){
        return EntityConstructors.hasSpeakers(af);
    }

    /**
     * Whether the user sign up for event or not
     * @return boolean that shows sign up or not
     */
    public boolean hasSignUp(){
        return EventDealer.hasSignUp(username,af);
    }

    /**
     * Whether there is a request or not
     */
    public boolean hasRequest(){
            return RequestDealer.hasRequest(reqm);
    }

    /**
     * Check if there is an event or not in the list
     */
    public boolean hasEvent(){
        return EventDealer.hasEvent(ef);
    }

    /**
     * Whether there is available event for the user or not
     * @return true if there is
     */
    public boolean hasAvailableEvent(){
        return ScheduleGetter.hasAvailableEvent(ef,username,rm,af);
    }

    /**
     * Whether the user have send message before or not by message type
     * @param box: the type of message
     */
    public boolean hasMessage(String box){
        return MessageDealer.hasMessage(username,mm,box);
    }

    /**
     * Whether there is empty room or not
     */
    public boolean hasRoom(){
        return EntityConstructors.hasRoom(rm);
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerAvailable(String date){
        Getter.speakerAvailable(this.af, date, cm);
    }

    /**
     * printout all rooms and their available times.
     */
    public void roomList(String date, int maxCapacity, ArrayList<String> constraints){
        Getter.roomList(date, this.rm, maxCapacity, constraints,cm);
    }

    /**
     * Whether there is available room or not
     */
    public boolean hasAvailableRoom(int maxCapacity, ArrayList<String> constraints){
        return Getter.hasAvailableRoom(rm, maxCapacity,constraints);
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
    public void attendeeSchedule(String sort, Map<String, String> filter) {
        ScheduleGetter.attendeeSchedule(this.af, this.username, ef, sort, filter);
    }

    /**
     * Get all liked events
     * @param sort: the type of sort
     * @param filter: the filter for the Event
     */
    public void getLikedEvents(String sort, Map<String, String> filter) {
        ScheduleGetter.getLikedEvents(username, sort, filter, af, ef);
    }

    /**
     * printout all events this attendee can signed up for. That is, events that are not full, the rooms where those
     * events take place haven't reach the the rooms' maximum capacity, and this attendee hasn't sign up for the event,
     * yet.
     */
    public void getAvailableEvent(String sort,Map<String, String> filter){
        ScheduleGetter.getAvailableEvent(this.rm, this.username, sort, filter,ef,af);
    }

    /**
     * Get the enrollment Statistics of the event
     */
    public void getEnrollmentStatistics(){
        Getter.enrollmentStatistics(ef,rm);
    }

    /**
     * Get the top five events that User sign up for
     */
    public void getTopFiveLists(){
        Getter.getTopFiveEvents(ef);
    }

    /**
     * Get the app traffic map
     */
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
     * @param message The message this user wants to send.
     */
    public void privateMessageTo(String receiver, String message){
        MessageDealer.privateMessageTo(receiver,message,this.mm,this.username);
    }

    /**
     * printout all usernames of users who had sent messages to this user.
     */
    public void checkContacts(String box){
        UserPrinter.checkContacts(this.mm,this.username,box);
    }

    /**
     * printout all usernames of users in the system.
     */
    public void checkUsers(){
        UserPrinter.checkUsers(af,username);
    }

    /**
     * Printout all messages sent from this user.
     * @param sender The username of the user who sent messages to this user.
     */
    public void getMessage(String sender, String box){
        MessageDealer.getMessage(sender,this.mm,this.username,box);
    }

    /**
     * Printout all events in the system.
     */
    public void getAllEvents(){
        EventDealer.getAllEvents(ef);
    }

    /**
     * Printout all usernames of attendees who attend this event.
     * @param eventId The Id of the certain event that is being chosen.
     */
    public void checkAudiences(Integer eventId){
        UserPrinter.checkAudiences(eventId,ef);
    }

    /**
     * Print all request from the list.
     */
    public void checkAllRequest() {
        RequestDealer.checkAllRequest(reqm);
    }

    /**
     * Print all request from a specific User
     */
    public void checkMyRequest(){RequestDealer.checkMyRequest(username,reqm);}

    /**
     * Addressed a request
     * @param requestId: the Id of the Request
     */
    public void addressRequest(int requestId) {
        RequestDealer.tagRequest(requestId, reqm);
    }


    /**
     * Mark a message as unread
     * @param sender: the username of the sender who send the message
     * @param index: the index of the message +1
     * @param box: the type of Message
     */
    public void markAsUnread(String sender, int index,String box) {
        MessageDealer.markAsUnread(username,mm,sender,index,box);
    }

    /**
     * Delete a message
     * @param sender: the username of the sender
     * @param index: the index of the message +1
     * @param box: the type of message
     */
    public void deleteMessage(String sender, int index, String box) {
        MessageDealer.deleteMessage(username,mm, sender,index, box);
    }

    /**
     * Archive a message
     * @param sender: the username of the User who send the message
     * @param index: the index of the message +1
     */
    public void archiveMessage(String sender, int index) {
        MessageDealer.archiveMessage(username,mm, sender,index);
    }

    /**
     * Cancel an event
     * @param eventId: the Id of the event
     */
    public void cancelEvent(int eventId) {
        EventDealer.cancelEvent(eventId, ef, af, rm, cm);
    }

    /**
     * Make a new request
     * @param request: the content of the request
     */
    public void madeRequest(String request) {
        RequestDealer.madeRequest(reqm, request,username);
    }

    /**
     * Like an event
     * @param eventId: the id of the event
     */
    public void likeEvent(int eventId) {
        EventDealer.likeEvent(eventId,af,username);
    }

    /**
     * Change user's access level to VIP event
     * @param username: the username of the User
     * @param access: the access level of the User
     */
    public void changeUserAccess(String username, String access){
        UserPrinter.changeUserAccess(username,af,access);
    }

    /**
     * Check whether the user can access the VIP events
     * @return VIP if the user is an VIP, and normal if the user is not
     */
    public String checkAccess(){
        return af.checkAccess(username);
    }

    /**
     * Whether the room is valid or not
     * @param roomId: the Id of the room
     * @return true if the room is valid
     */
    public boolean validRoom(int roomId){
        return EntityConstructors.validRoom(roomId,rm);
    }

    /**
     * Whether the speaker is in the list or not
     * @param speaker: the username of the Speaker
     * @return true if the Speaker is in the list
     */
    public boolean validSpeaker(String speaker){
        return EntityConstructors.validSpeaker(speaker,af);
    }

    /**
     * Whether the request is in the list or not
     * @param id: the id of the request
     * @return true if the request is in the list
     */
    public boolean validRequest(int id){
        return RequestDealer.containRequest(reqm,id);
    }
}
