package Usecase;

import Entity.User;
import Entity.VIP;

import java.io.Serializable;
import java.util.HashMap;

public class VipAct extends Act implements Serializable {
    public HashMap<String, VIP> vipMap;


    /**
     * Create a new VIP user.
     *
     * @param name
     * @param username
     * @param password
     */
    @Override
    public boolean createUser(String name, String username, String password) {
        if(vipMap.containsKey(username)){
            return false;
        }
        VIP vip = new VIP(name,username,password);
        vipMap.put(vip.getUsername(),vip);
        return true;
    }

    /**
     * Get the user information from the map.
     *
     * @param username : the username of the user
     */
    @Override
    public User getUser(String username) {
        return vipMap.get(username);
    }

    /**
     * Sign up for an event.
     *
     * @param username : the username of the user
     * @param eventId  : the event id
     * @return if the user sign up succesfully
     */
    @Override
    public boolean signUp(String username, Integer eventId) {
        if (getUser(username).getSignUp().contains(eventId)){
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }
}
