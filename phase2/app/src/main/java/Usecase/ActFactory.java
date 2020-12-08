package Usecase;

import Entity.*;

import java.lang.reflect.Array;
import java.util.*;

public class ActFactory {

    public HashMap<String,User> userHashMap;
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
        if(type == "Organizer") {
            User organizer = new Organizer(name, username,password);
            userHashMap.put(username,organizer);
        } else if (type == "Speaker") {
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
        return getUser(username).validatePassword(password);
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

    /**
     * Remove an event in the eventList of user
     * @param username: the username of the user
     * @param eventId: the event id that user want to remove
     */
    public void removeFromEvent(String username, int eventId) {
        getUser(username).getSignUp().remove(eventId);
    }

    public boolean checkUsernameTaken(String username) {
        if(userHashMap.containsKey(username)) {
            System.out.println("This username has been taken.");
            return true;
        }
        return false;
    }

    public ArrayList<Integer> checkLikedEvents(String username) {
        return getUser(username).getLikedEvents();
    }

    public boolean likeEvent(String username, int eventId) {
        if(getUser(username).getLikedEvents().contains(eventId)) {
            return false;
        }
        getUser(username).getLikedEvents().add(eventId);
        return true;
    }
    public String checkAccess(String username) {
        return getUser(username).getAccess();
    }

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