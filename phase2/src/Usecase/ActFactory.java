package Usecase;

import Entity.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class ActFactory implements Serializable {

    public HashMap<String,User> userHashMap;

    public ActFactory() {
        userHashMap = new HashMap<>();
    }
    /**
     * Create a new user and return true if the creation is successful.
     * @param name: the name of the attendee.
     * @param username: the username of the attendee.
     * @param password: the password of the attendee.
     */
    public boolean createUser(String name, String username, String password, String type) {
        if(userHashMap.containsKey(username)) {
            return false;
        }
        if(type.equals("Organizer")) {
            User organizer = new Organizer(name, username,password);
            userHashMap.put(username,organizer);
        } else if (type.equals("Speaker")) {
            User speaker = new Speaker(name, username,password);
            userHashMap.put(username,speaker);
        } else {
            User attendee = new User(name, username,password);
            userHashMap.put(username,attendee);
        }
        return true;
    }

    /**
     * log in to the program and return whether the user enter the correct password.
     * @param username: the username of the user.
     * @param password: the password of the user.
     * @return boolean that whether the password is correct or not.
     */
    public boolean login(String username, String password){
        if (getUser(username)==null) {
            return false;
        }
        if(!getUser(username).validatePassword(password)){
            System.out.println("Username and password do not match.");
            return false;
        }
        return true;
    }

    /**
     * Get the user information from the map.
     *
     * @param username: the username of the user
     */
    public User getUser(String username) {
        return userHashMap.get(username);
    }

    /**
     * Sign up for an event.
     * @param username: the username of the user
     * @param eventId: the event id
     * @return if the user sign up successfully
     */
    public boolean signUp(String username, Integer eventId) {
        if(getUser(username).getSignUp().contains(eventId)) {
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

    /**
     * Get the list of event id that user sign up
     * @param username: the username of the user
     * @return list of event that user in
     */
    public ArrayList<Integer> getEvents(String username){
        return getUser(username).getSignUp();
    }

    public ArrayList<Integer> givenEvents(String username,Date date){
        Speaker speaker = (Speaker)getUser(username);
        return speaker.getGiveEvents_OneDay(date);
    }

    /**
     * Remove an event in the eventList of user
     * @param username: the username of the user
     * @param eventId: the event id that user want to remove
     */
    public void removeFromEvent(String username, int eventId) {
        getUser(username).getSignUp().remove(eventId);
    }

    public boolean checkUsernameTaken(String username) {
        return userHashMap.containsKey(username);
    }

    /**
     * Get a list of liked event of a user
     * @param username: the username of the User
     */
    public ArrayList<Integer> checkLikedEvents(String username) {
        return getUser(username).getLikedEvents();
    }

    /**
     * Add a new liked event to the list
     * @param username: the username of the User
     * @param eventId: Id of the event
     * @return the boolean that whether the event is successfully added to the list
     */
    public boolean likeEvent(String username, int eventId) {
        if(getUser(username).getLikedEvents().contains(eventId)) {
            return false;
        }
        getUser(username).getLikedEvents().add(eventId);
        return true;
    }

    /**
     * Get access
     * @param username: the username of the User
     */
    public String checkAccess(String username) {
        return getUser(username).getAccess();
    }

    /**
     * Change access
     * @param username: the username of the User
     * @param access: user's access
     */
    public void changeAccess(String username, String access) {
        getUser(username).setAccess(access);
    }

    /**
     * Returns an hash map of events that the speaker will present.
     *
     * @param username the username
     * @return the hash map
     */
    public HashMap<Date, ArrayList<Integer>> allEventList(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getAllGivenEvents();
    }


    /**
     * Puts an event in speaker's events list. If the speaker are available to add the event, return True.
     *
     * @param username the username
     * @param eventId  the event id
     * @param date     the date
     * @return the boolean
     */
    public void giveEvent(String username,Integer eventId, Date date){
        Speaker speaker = (Speaker) getUser(username);
        speaker.getGiveEvents_OneDay(date).add(eventId);
    }

    /**
     * Cancels an event. If successfully cancels the event, return true
     *
     * @param username the username
     * @param eventId  the event id
     * @return the boolean
     */
    public void cancelEvent(String username, Integer eventId, Date date) {
        Speaker speaker = (Speaker) getUser(username);
        speaker.getGiveEvents_OneDay(date).remove(eventId);
    }


    /**
     * get all speakers.
     */
    public ArrayList<String> speakerList() {
        ArrayList<String> speakers = new ArrayList<>();
        for(String username: userHashMap.keySet()){
            if(userHashMap.get(username) instanceof Speaker) {
                speakers.add(username);
            }
        }
        return speakers;
    }

    /**
     * get all organizers.
     */
    public ArrayList<String> organizerList() {
        ArrayList<String> organizers = new ArrayList<>();
        for(String username: userHashMap.keySet()){
            if(userHashMap.get(username) instanceof Organizer) {
                organizers.add(username);
            }
        }
        return organizers;
    }

    /**
     * get all attendees.
     */
    public ArrayList<String> attendeeList() {
        ArrayList<String> attendees = new ArrayList<>();
        for(String username: userHashMap.keySet()){
            if(!speakerList().contains(username) && !organizerList().contains(username)) {
                attendees.add(username);
            }
        }
        return attendees;
    }
}
