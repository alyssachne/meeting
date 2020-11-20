package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * The type Speaker act.
 */
public class SpeakerAct extends Act implements Serializable {

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
     * Gets user from Speakermap by its id.
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

    /**
     * returns an array list of available time from a speaker.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<Integer> availableTime(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getAvailable();
    }

    /**
     * Returns an array list of events that the speaker will present.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<Integer> eventList(String username){
        Speaker speaker = (Speaker) getUser(username);
        return speaker.getGiveEvents();
    }

    /**
     * printout all speakers and their available times.
     */
    public void speakerList(){
        for (String username:speakerMap.keySet()){
            System.out.println(username);
            for (Integer time: availableTime(username)){
                System.out.println(time);
            }
        }
    }

    /**
     * Puts an event in speaker's events list. If the speaker are available to add the event, return True.
     *
     * @param username the username
     * @param eventId  the event id
     * @param time     the time
     * @return the boolean
     */
    public boolean giveEvent(String username,Integer eventId, Integer time){
        Speaker speaker = (Speaker) getUser(username);
        if(speaker.getAvailable().contains(time)) {
            speaker.events.put(time, eventId);
            return true;
        }
        return false;
    }

    /**
     * Cancels an event. If successfully cancels the event, return true
     *
     * @param username the username
     * @param eventId  the event id
     * @return the boolean
     */
    public boolean cancelEvent(String username, Integer eventId) {
        Speaker speaker = (Speaker) getUser(username);
        Set<Integer> temp = speaker.events.keySet();
        for(Integer time: temp) {
            if(speaker.events.get(time).equals(eventId)) {
                speaker.events.replace(time, null);
                return true;
            }
        }
        return false;
    }

}
