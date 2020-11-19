package Usecase;

import Entity.*;

import java.util.ArrayList;

public abstract class Act {

    public abstract void createUser(String name, String username, String password);

    public boolean login(String username, String password){
        return password.equals(getUser(username).getPassword());
    }

    public abstract User getUser(String username);

    public abstract boolean signUp(String username, Integer eventId);

    public ArrayList<Integer> getEvents(String username){
        return getUser(username).getSignUp();
    }

    public void addMessage(String receiver, String sender, String message){
        if (getUser(receiver).messageInbox.containsKey(sender)){
            getUser(receiver).messageInbox.get(sender).add(message);
        }else{
            ArrayList<String> arr = new ArrayList<>();
            arr.add(message);
            getUser(receiver).messageInbox.put(sender,arr);
        }
    }

    public ArrayList<String> getContacts(String username){
        return getUser(username).getContacts();
    }

    public ArrayList<String> getMessage(String receiver, String sender){
        return getUser(receiver).getMessage(sender);
    }

    public void cancelSpot(String username, int eventId) {
        getUser(username).eventList.remove(eventId);
    }
}
