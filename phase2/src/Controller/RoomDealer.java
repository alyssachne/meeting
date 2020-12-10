package Controller;

import Entity.Room;
import Usecase.RoomManager;

import java.io.Serializable;
import java.util.List;

public class RoomDealer implements Serializable {
    public static void updateRoom(List<String> newFeatures, RoomManager rm, int roomId) {
        try {
            for(String f: newFeatures) {
                rm.addNewFeature(roomId, f);
            }
        }
        catch(Exception e) {
            System.out.println("This feature has already exist.");
        }
    }

    // clean the room by remove oldFeatures
    public static void cleanRoom(List<String> oldFeatures, RoomManager rm, int roomId) {
        try {
            for(String f: oldFeatures) {
                rm.removeFeature(roomId, f);
            }
        }
        catch(Exception e) {
            System.out.println("This feature does not exist, hence, it cannot be removed.");
        }
    }
}
