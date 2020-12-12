package Usecase;

import Entity.Organizer;
import Entity.Speaker;
import Entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The Usecase class for the actions of users.
 */

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
     * @param username: the username of the user.
     */
    public User getUser(String username) {
        return userHashMap.get(username);
    }

    /**
     * Sign up for an event.
     * @param username: the username of the user.
     * @param eventId: the event id.
     * @return if the user sign up successfully.
     */
    public boolean signUp(String username, Integer eventId) {
        if(getUser(username).getSignUp().contains(eventId)) {
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

    /**
     * Get the list of event id that user sign up.
     * @param username: the username of the user.
     * @return list of event that user in.
     */
    public ArrayList<Integer> getEvents(String username){
        return getUser(username).getSignUp();
    }

    public ArrayList<Integer> givenEvents(String username,Date date){
        Speaker speaker = (Speaker)getUser(username);
        return speaker.getGiveEvents_OneDay(date);
    }

    /**
     * Remove an event in the eventList of user.
     * @param username: the username of the user.
     * @param eventId: the event id that user want to remove.
     */
    public void removeFromEvent(String username, int eventId) {
        getUser(username).getSignUp().remove(eventId);
    }

    /**
     * Check if the username has already be taken.
     * @param username: the username of the user.
     * @return the boolean value of whether the username has been taken.
     */
    public boolean checkUsernameTaken(String username) {
        return userHashMap.containsKey(username);
    }

    /**
     * Get a list of liked event of a user.
     * @param username: the username of the user
     */
    public ArrayList<Integer> checkLikedEvents(String username) {
        return getUser(username).getLikedEvents();
    }

    /**
     * Add a new liked event to the list
     * @param username: the username of the user
     * @param eventId: Id of the event
     * @return the boolean that whether the event is successfully added to the list
     */
    public boolean likeEvent(String username, int eventId) {
        if(getUser(username).getLikedEvents().contains(eventId)) {
            return false;
        }
        getUser(username).setLikeEvent(eventId);
        return true;
    }

    /**
     * Get access of the user.
     * @param username: the username of the User.
     */
    public String checkAccess(String username) {
        return getUser(username).getAccess();
    }

    /**
     * Change access of the user.
     * @param username: the username of the user.
     * @param access: the user's access.
     */
    public void changeAccess(String username, String access) {
        getUser(username).setAccess(access);
    }

    /**
     * Returns an hash map of events that the speaker will present.
     * @param username: the username of the speaker.
     * @return the hash map of the events that the speaker will present.
     */
    public HashMap<Date, ArrayList<Integer>> allEventList(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getAllGivenEvents();
    }


    /**
     * Puts an event in speaker's events list. If the speaker is available to add the event, return True.
     * @param username: the username of the speaker.
     * @param eventId: the event id of the input event.
     * @param date: the date of the event.
     */
    public void giveEvent(String username,Integer eventId, Date date){
        Speaker speaker = (Speaker) getUser(username);
        speaker.getGiveEvents_OneDay(date).add(eventId);
    }

    /**
     * Cancels an event. If successfully cancels the event, return true.
     * @param username: the username of the speaker.
     * @param eventId: the event id of the event to be cancelled.
     */
    public void cancelEvent(String username, Integer eventId, Date date) {
        Speaker speaker = (Speaker) getUser(username);
        speaker.getGiveEvents_OneDay(date).remove(eventId);
    }


    /**
     * Get the arraylist that contains all speakers' usernames.
     * @return the arraylist of the usernames of all the speakers.
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
     * Get the arraylist that contains all organizers' usernames.
     * @return the arraylist of the usernames of all the organizers.
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
     * Get the arraylist that contains all attendees' usernames.
     * @return the arraylist of the usernames of all the attendees.
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
