package Sorter;

import Entity.Event;
import java.util.ArrayList;

public class EventIdSorter implements Sorter {

    public EventIdSorter(){}


    @Override
    // Selection Sort
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest id in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (tl.get(indexMin).getId() > tl.get(j).getId()) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest id
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }

}
