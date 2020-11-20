package Usecase;

import Entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Speaker act.
 */
public class SpeakerAct implements Usable, Serializable {

    /**
     * The Speaker map.
     */
    public HashMap<String,Speaker> speakerMap;

    /**
     * Instantiates a new Speaker act.
     */
    public SpeakerAct(){
        speakerMap = new HashMap<>();
    }

    /**
     * Create speaker.
     *
     * @param name     the name
     * @param username the username
     * @param password the password
     */
    public void createSpeaker(String name, String username, String password) {
        Speaker speaker = new Speaker(name, username, password);
        speakerMap.put(speaker.getUsername(),speaker);
    }

    /**
     * Get speaker speaker.
     *
     * @param username the username
     * @return the speaker
     */
    public Speaker getSpeaker(String username){
        return speakerMap.get(username);
    }

    /**
     * Available time array list.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<Integer> availableTime(String username){
        Speaker speaker = getSpeaker(username);
        return speaker.getAvailable();
    }

    /**
     * Event list array list.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<Integer> eventList(String username){
        Speaker speaker = getSpeaker(username);
        return speaker.getGiveEvents();
    }

    /**
     * Add message.
     *
     * @param receiver the receiver
     * @param sender   the sender
     * @param message  the message
     */
    public void addMessage(String receiver, String sender, String message){
        getSpeaker(receiver).addMessage(sender,message);
    }

    /**
     * Login boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean login(String username, String password){
        if (password.equals(getSpeaker(username).getPassword())){
            return true;
        }
        return false;
    }

    /**
     * Speaker list.
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
     * Get an array list of Users that attends speaker's events.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<String> getContacts(String username){
        return getSpeaker(username).getContacts();
    }

    /**
     * Get an array list of messages.
     *
     * @param receiver the receiver
     * @param sender   the sender
     * @return the array list
     */
    public ArrayList<String> getMessage(String receiver, String sender){
        return getSpeaker(receiver).getMessage(sender);
    }

    /**
     * Sign up and return result of sign up.
     *
     * @param username the username
     * @param eventId  the event id
     * @return the boolean
     */
    @Override
    public boolean signUp(String username, int eventId) {
        return getSpeaker(username).signUp(eventId);
    }

    /**
     * Cancel spot and return whether cancels successfully.
     *
     * @param username the username
     * @param eventId  the event id
     * @return the boolean
     */
    @Override
    public boolean cancelSpot(String username, int eventId) {
        return getSpeaker(username).cancelSpot(eventId);
    }

    /**
     * Give Arraylist of events that speaker attends.
     *
     * @param username the username
     * @param eventId  the event id
     * @param time     the time
     */
    public void giveEvent(String username,Integer eventId, Integer time){
        speakerMap.get(username).giveEvent(eventId,time);
    }

    /**
     * Cancel event and return result.
     *
     * @param event   the event
     * @param speaker the speaker
     * @return the boolean
     */
    public boolean cancelEvent(Event event, Speaker speaker) {
        return speaker.cancelEvent(event.id);
    }


}
