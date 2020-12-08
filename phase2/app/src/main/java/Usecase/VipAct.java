package Usecase;

import Entity.User;
import Entity.VIP;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

public class VipAct extends ActFactory implements Serializable {
    public HashMap<String, VIP> vipMap;


    /**
     * Create a new VIP user.
     *
     * @param name: the name of this VipUser
     * @param username: the username of this VipUser
     * @param password: the password of this user
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
     * @return if the user sign up successfully
     */
    @Override
    public boolean signUp(String username, Integer eventId) {
        if (getUser(username).getSignUp().contains(eventId)){
            return false;
        }
        getUser(username).eventList.add(eventId);
        return true;
    }

    @Override
    public boolean checkUsernameTaken(String username) {
        return vipMap.containsKey(username);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("You have a new maeesage from"+ arg);
    }
}
