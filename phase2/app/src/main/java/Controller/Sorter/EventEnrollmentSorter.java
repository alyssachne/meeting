package Controller.Sorter;

import Usecase.EventFactory;

import java.util.ArrayList;

public class EventEnrollmentSorter implements SorterStrategy {
    @Override
    public void sort(ArrayList<Integer> tl, EventFactory ef) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest number of enrollment in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (ef.getEvent(tl.get(indexMin)).getNumOfAttendees() > ef.getEvent(tl.get(j)).getNumOfAttendees()) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest roomId
            // between i and  list.size() - 1 inclusive.
            int temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}