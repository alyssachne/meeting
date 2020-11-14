package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {
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

    public void addMessage(String username, String message){
        if (messageInbox.containsKey(username)){
            messageInbox.get(username).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            messageInbox.put(username,arr);
        }
    }

    public ArrayList<String> getContacts(){
        ArrayList<String> arr = new ArrayList<>();
        for (String name : messageInbox.keySet()){
            arr.add(name);
        }
        return arr;
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

    public ArrayList<Integer> getEventList() {
        return eventList;
    }

    public boolean signUp(Integer eventId) {
        if(eventList.contains(eventId)) {
            return false;
        }
        eventList.add(eventId);
        return true;
    }

    public boolean cancelSpot(Integer eventId) {
        return eventList.remove(eventId);
    }

    public String typeGetter(){return "User";};

}