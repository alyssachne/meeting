package Controller;

import Usecase.RoomManager;

import java.io.Serializable;
import java.util.List;

public class RoomDealer implements Serializable {

    /**
     * Add new features to room
     * @param newFeatures: list of new features
     * @param rm: room manager
     * @param roomId: the id of the room
     */
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

    /**
     * Clean some old features from the room
     * @param oldFeatures: the list of old features
     * @param rm: room manager
     * @param roomId: the Id of the room
     */
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
