package Entity;

import Usecase.MessageHistory;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name;
    private String username;
    private String password;
    //private MessageBox InBox;
    //private MessageBox SentBox;
    //private HashMap<String, MessageHistory> messageList = new HashMap<>();
    public ArrayList<Integer> eventList;
    public ArrayList<String> contacts;

    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        eventList = new ArrayList<>();
        contacts = new ArrayList<>();
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

    public ArrayList<String> getContacts() {
        return contacts;
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

    public boolean addContact(String username) {
        if(contacts.contains(username)) {
            return false;
        }
        contacts.add(username);
        return true;
    }

    public boolean removeContact(String username) {
        return contacts.remove(username);
    }

//    public void sendMessage(Message message){
//        this.SentBox.addMessage(message);
//    }
//
//    public void receiveMessage(Message message){
//        this.InBox.addMessage(message);
//    }
//
//    public void viewMessageIn() {
//        System.out.println(this.InBox);
//    }

    public String typeGetter(){return "User";};

}