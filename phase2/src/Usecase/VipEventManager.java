package Usecase;

import Entity.Event;
import Entity.EventParent;
import Entity.VipEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class VipEventManager extends EventManager implements Serializable {

    private HashMap<Integer, VipEvent> VipEventHashMap;

    /**
     * Create a new event.
     *
     * @param id          : the id of the event
     * @param title: the title of the VIP Event
     * @param time: the time of the VIP Event
     * @param roomId: the integer id indicating where the event will be hold
     * @param speakers: a list of strings representing the speaker of this event
     * @param duration: the duration of this vip event
     * @param maxCapacity: the maximum people that can be accommodated by the event
     * @param eventAccess:-
     * @param constraints:-
     */
    @Override
    public void createEvent(int id, String title, int time, int roomId, List<String> speakers, int duration, int maxCapacity, String eventAccess, List<String> constraints) {
        VipEvent newVipEvent = new VipEvent(id,title,time,roomId,speakers,duration,maxCapacity,eventAccess,constraints);
        VipEventHashMap.put(id,newVipEvent);
    }

    @Override
    public boolean cancelEvent(int id) {
        if (VipEventHashMap.containsKey(id)){
            VipEventHashMap.remove(id);
            return true;
        }
        else {
            System.out.println("This VipEvent does not exist");
            return false;
        }
    }

    @Override
    public boolean containEvent(int id) {
        return VipEventHashMap.containsKey(id);
    }

    /**
     * Return the event of the corresponding id, raise error if not found.
     *
     * @param id : the id of the event.
     */
    @Override
    public Event getEvent(int id) {
        try {
            return VipEventHashMap.get(id);
            }
        catch (Exception e){
            System.out.println("This VIP Event does not exist!");
        }
        return null;
    }
}
