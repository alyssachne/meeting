//package Usecase;
//
//import Entity.*;
//
//import java.io.Serializable;
//import java.util.*;
//
//public class AttendeeAct extends ActFactory implements Serializable, Observer {
//    public HashMap<String,Attendee> attendeeMap;
//
//    /**
//     * Initialize an AttendeeAct.
//     */
//    public AttendeeAct(){
//      attendeeMap = new HashMap<>();
//    }
//
//
//    /**
//     * Create a new attendee and return true if the creation is successful.
//     * @param name: the name of the attendee.
//     * @param username: the username of the attendee.
//     * @param password: the password of the attendee.
//     */
//    @Override
//    public boolean createUser(String name, String username, String password) {
//        if(attendeeMap.containsKey(username)) {
//            return false;
//        }
//        Attendee attendee = new Attendee(name, username, password);
//        attendeeMap.put(attendee.getUsername(),attendee);
//        return true;
//    }
//
//    /**
//     * Return an attendee by the username.
//     * @param username: the username of the attendee.
//     */
//    @Override
//    public User getUser(String username) {
//            return attendeeMap.get(username);
//        }
//
//    /**
//     * Sign up a event for an attendee and return true if signup successful.
//     * @param username: the username of the attendee.
//     * @param eventId: the id of the event.
//     */
//    @Override
//    public boolean signUp(String username, Integer eventId) {
//        if(getUser(username).getSignUp().contains(eventId)) {
//            return false;
//        }
//        getUser(username).eventList.add(eventId);
//        return true;
//    }
//
//    public boolean checkUsernameTaken(String username) {
//        if(attendeeMap.containsKey(username)) {
//            System.out.println("This username has been taken.");
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        System.out.println("You have a new message from " + arg);
//    }
//}
