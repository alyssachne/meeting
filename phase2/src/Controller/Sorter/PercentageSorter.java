package Controller.Sorter;

import Usecase.EventFactory;
import Usecase.RoomManager;

import java.util.ArrayList;

    /**
     * The class to implement sorter by the percentage of attendance
     * (max capacity of the room divided by number of attendees).
     */

public class PercentageSorter {

        /**
         * A function to implement sort by sorting the events according to their percentage of attendance.
         * @param tl An arraylist of the ids of the event.
         * @param ef EventFactory in use case.
         * @param rm RoomManager in use case.
         */

    public void sort(ArrayList<Integer> tl, EventFactory ef, RoomManager rm) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest number of enrollment in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                float percentmin = (float)ef.getEvent(tl.get(indexMin)).getNumOfAttendees() / rm.getRoom(ef.getEvent(tl.get(indexMin)).getRoom()).getMaxCapacity();
                float percentj = (float) ef.getEvent(tl.get(j)).getNumOfAttendees() / rm.getRoom(ef.getEvent(tl.get(j)).getRoom()).getMaxCapacity();
                if ( percentmin> percentj ) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest percentage of attendance
            // between i and list.size() - 1 inclusive.
            int temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
