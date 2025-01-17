package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The abstract entity class for all Users, the parent class of,{@link Speaker} and {@link Organizer}
 *
 * Defines the structure that all User type objects should share: getters, setters & framework of interaction with
 * message.
 */

public class User implements Serializable {
    private String name;
    private String username;
    private String password;
    public ArrayList<Integer> eventList;
    private ArrayList<Integer> likedEvent;
    private String access;

    /**
     * Initialize an user.
     * @param name: the name of the user.
     * @param username: the username of the user.
     * @param password: the password of the user.
     */
    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.access = "Normal";
        eventList = new ArrayList<>();
        likedEvent = new ArrayList<>();
    }

    /**
     * Get the name of this user.
     */
    public String getName(){
        return name;
    }

    /**
     * Set the name of this user.
     * @param name: the new name of this user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the username of this user.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Set the username of this user.
     * @param username: the new username of this user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Validate the password of this user.
     */
    public boolean validatePassword(String password){
        return this.password.equals(password);
    }

    /**
     * Set the password of this user.
     * @param password: the new password of this user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get a list of id of the event this user signed up for.
     */
    public ArrayList<Integer> getSignUp() {
        return eventList;
    }

    /**
     * Getter for the liked event list of a user.
     * @return likedEvent , a list of liked events marked by the user.
     */
    public ArrayList<Integer> getLikedEvents() {
        return likedEvent;
    }

    /**
     * Setter for the liked event list, will add the parameter event to the liked event list.
     * @param id, the id of the liked event to be added to the list.
     */
    public void setLikeEvent(int id){
        likedEvent.add(id);
    }

    /**
     * getter for the access of this user.
     * @return access, the String representing the access granted to the user, if the returned string could be either
     * "VIP" or "Normal".
     */
    public String getAccess() {
        return access;
    }

    /**
     * setter for the access of this user.
     * @param  newAccess, the String representing the access granted to the user
     */
    public void setAccess(String newAccess) {
        access = newAccess;
    }

    /**
     * Get the type of the user.
     */
    public String typeGetter(){return "User";}


}