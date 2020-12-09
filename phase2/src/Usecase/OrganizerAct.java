//package Usecase;
//import Entity.*;
////import org.omg.CORBA.Any;
//
//import java.io.Serializable;
//import java.util.*;
//
//public class OrganizerAct extends ActFactory implements Serializable, Observer {
//
//    public HashMap<String,Organizer> organizerMap;
//
//    /**
//     * Initialize an OrganizerAct.
//     */
//    public OrganizerAct(){
//        organizerMap = new HashMap<>();
//    }
//
//    /**
//     * Create a new organizer and return true if the creation is successful.
//     * @param name: the name of the organizer.
//     * @param username: the username of the organizer.
//     * @param password: the password of organizer.
//     */
//    @Override
//    public boolean createUser(String name, String username, String password) {
//        if(organizerMap.containsKey(username)) {
//            return false;
//        }
//        Organizer organizer = new Organizer(name,username,password);
//        organizerMap.put(organizer.getUsername(),organizer);
//        return true;
//    }
//
//    /**
//     * Return an organizer by the username.
//     * @param username: the username of the user.
//     */
//    @Override
//    public User getUser(String username) {
//        return organizerMap.get(username);
//    }
//
//    /**
//     * Sign up an event for an organizer and return true if signup successful.
//     * @param username: the username of the user.
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
//        if(organizerMap.containsKey(username)) {
//            System.out.println("This username has been taken.");
//            return true;
//        }
//        return false;
//    }
//}
