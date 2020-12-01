package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
    //<username,messageList>
    public HashMap<String,ArrayList<String>> messageInbox;

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
        messageInbox = new HashMap<>();
    }

    /**
     * Get a list of usernames of the users who had sent message to this user.
     */

    public ArrayList<String> getContacts(){
        return new ArrayList<>(messageInbox.keySet());
    }

    /**
     * Get a list of messages sent from the sender of this username.
     * @param username: the username of the message sender.
     */
    public ArrayList<String> getMessage(String username){
        return messageInbox.get(username);
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
     * Get the password of this user.
     */
    public String getPassword(){
        return password;
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
     * Get the type of the user.
     */
    public String typeGetter(){return "User";}

}