package Usecase;

import Entity.Event;
import Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Act {

    /**
     * Create a new user.
     */
    public abstract boolean createUser(String name, String username, String password);

    /**
     * log in to the program and return whether the user enter the correct password.
     * @param username: the username of the user.
     * @param password: the password of the user.
     * @return boolean that whether the password is correct or not.
     */
    public boolean login(String username, String password){
        return password.equals(getUser(username).getPassword());
    }

    /**
     * Get the user information from the map.
     * @param username: the username of the user
     */
    public abstract User getUser(String username);

    /**
     * Sign up for an event.
     * @param username: the username of the user
     * @param eventId: the event id
     * @return if the user sign up succesfully
     */
    public abstract boolean signUp(String username, Integer eventId);

    /**
     * Get the list of event id that user sign up
     * @param username: the username of the user
     * @return list of event that user in
     */
    public ArrayList<Integer> getEvents(String username){
        return getUser(username).getSignUp();
    }

    /**
     * Add a new message to the messageInbox
     * @param receiver: User who receive the message
     * @param sender: User who send the message
     * @param message: The content of the message
     */
    public void addMessage(String receiver, String sender, String message){
        if (getUser(receiver).messageInbox.containsKey(sender)){
            getUser(receiver).messageInbox.get(sender).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            getUser(receiver).messageInbox.put(sender,arr);
        }
    }

    /**
     * Get the list of contact of the user
     * @param username: the username of the user
     * @return list of user in User's contact
     */
    public ArrayList<String> getContacts(String username){
        return getUser(username).getContacts();
    }

    /**
     * Get list of message between two users
     * @param receiver: the user who receive the message
     * @param sender: the user who send the message
     * @return the list of message between two users
     */
    public ArrayList<String> getMessage(String receiver, String sender){
        return getUser(receiver).getMessage(sender);
    }

    /**
     * Cancel an event in the eventList of user
     * @param username: the username of the user
     * @param eventId: the event id that user want to remove
     */
    public void cancelSpot(String username, int eventId) {
        getUser(username).eventList.remove(eventId);
    }

// in controller, get the username of attendees of this event and speakers of this event.
    public void eventCanceled(int eventId, List<String> attendees, List<String> speakers) {
        for(String a: attendees) {
            getUser(a).getSignUp().remove(eventId);
        }
        for(String s: speakers) {
            getUser(s).eventList.remove(eventId);
        }
    }
}
