package Usecase;

import Entity.Event;
import Entity.User;
//import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public abstract class Act implements Observer {

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
        return getUser(username).validatePassword(password);
    }

    /**
     * Get the user information from the map.
     *
     * @param username: the username of the user
     */
    public User getUser(String username) {
        return null;
    }

    /**
     * Sign up for an event.
     * @param username: the username of the user
     * @param eventId: the event id
     * @return if the user sign up successfully
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
     * Remove an event in the eventList of user
     * @param username: the username of the user
     * @param eventId: the event id that user want to remove
     */
    public void removeFromEvent(String username, int eventId) {
        getUser(username).getSignUp().remove(eventId);
    }

    public abstract boolean checkUsernameTaken(String username);

    public List<Integer> checkLikedEvents(String username) {
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
}
