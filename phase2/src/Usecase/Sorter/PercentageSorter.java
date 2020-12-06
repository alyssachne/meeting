package Usecase.Sorter;

import Entity.Event;

import java.util.ArrayList;

public class PercentageSorter implements Sorter {
    @Override
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest number of enrollment in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                float percentmin = (float)tl.get(indexMin).getNumOfAttendees() / tl.get(indexMin).getMaxCapacity();
                float percentj = tl.get(j).getNumOfAttendees() / tl.get(j).getMaxCapacity();
                if ( percentmin> percentj ) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest roomId
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
