package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The abstract entity class for all Users, the parent class of {@link Attendee},{@link Speaker} and {@link Organizer}
 *
 * Defines the structure that all User type objects should share: getters, setters & framework of interaction with
 * message.
 */

public abstract class User implements Serializable {
    private String name;
    private String username;
    private String password;
    public ArrayList<Integer> eventList;
    private List<Integer> likedEvent;
    private String access;
    private String dietaryHabit;

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

    public List<Integer> getLikedEvents() {
        return likedEvent;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String newAccess) {
        access = newAccess;
    }

    /**
     * Get the type of the user.
     */
    public String typeGetter(){return "User";}

    /**
     * DietaryHabit setter, used to document the user's dietary habit if special accommodation need to be made.
     * @param habit: a string representing the dietary habit of the user
     */
    public void setDietaryHabit(String habit){
        this.dietaryHabit = habit;
    }

}