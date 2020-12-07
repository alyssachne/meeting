package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.*;

/**
 * The type Speaker act.
 */
public class SpeakerAct extends Act implements Serializable, Observer {

    /**
     * The Speaker map that maps speakers' id and Speaker class.
     */
    public HashMap<String,Speaker> speakerMap;

    /**
     * Instantiates a new Speaker act.
     */
    public SpeakerAct(){
        speakerMap = new HashMap<>();
    }

    /**
     * Create user. If the speaker is already in the map return false.
     *
     * @param name     the name
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    @Override
    public boolean createUser(String name, String username, String password) {
        if(speakerMap.containsKey(username)) {
            return false;
        }
        Speaker speaker = new Speaker(name, username, password);
        speakerMap.put(speaker.getUsername(),speaker);
        return true;
    }

    /**
     * Gets user from speakerMap by its id.
     *
     * @param username the username
     * @return the user
     */
    @Override
    public User getUser(String username) {
        return speakerMap.get(username);
    }

    /**
     * Signs up a event for one speaker and return whether successfully Sign up.
     *
     * @param username the username
     * @param eventId  the event id
     * @return the boolean
     */
    @Override
    public boolean signUp(String username, Integer eventId) {
        ArrayList<Integer> given = getUser(username).getSignUp();
        if(getUser(username).eventList.contains(eventId) || given.contains(eventId)) {
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

//    /**
//     * returns an array list of available time from a speaker.
//     *
//     * @param username the username
//     * @return the array list
//     */
//    public ArrayList<Integer> availableTime(String username){
//        Speaker speaker = (Speaker) getUser(username);
//        return speaker.getAvailable();
//    }

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
     * printout all speakers and their available times.
     */
    public Set<String> speakerList() {
        return speakerMap.keySet();
    }
//            for (Integer time: availableTime(username)){
//                System.out.println(time);
//            }
//        }
//    }

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

    public boolean checkUsernameTaken(String username) {
        if(speakerMap.containsKey(username)) {
            return true;
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("You have a new message from " + arg);
    }
}
