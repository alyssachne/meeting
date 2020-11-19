package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class User implements Serializable {
    private String name;
    private String username;
    private String password;
    public ArrayList<Integer> eventList;
    //<username,messageList>
    public HashMap<String,ArrayList<String>> messageInbox;

    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        eventList = new ArrayList<>();
        messageInbox = new HashMap<>();
    }

    public ArrayList<String> getContacts(){
        return new ArrayList<>(messageInbox.keySet());
    }

    public ArrayList<String> getMessage(String username){
        return messageInbox.get(username);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getSignUp() {
        return eventList;
    }

    public String typeGetter(){return "User";};

}