package Controller.Sorter;

import Usecase.EventFactory;

import java.util.ArrayList;

    /**
     * The class to implement sorter by event id.
     */


public class EventIdSorter implements SorterStrategy {

    /**
     * A function to implement sort by sorting the events according to their event ids.
     * @param tl An arraylist of the ids of the event.
     * @param ef EventFactory in use case.
     */

    @Override
    // Selection Sort
    public void sort(ArrayList<Integer> tl, EventFactory ef) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest id in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (ef.getEvent(tl.get(indexMin)).getId() > ef.getEvent(tl.get(j)).getId()) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest id
            // between i and list.size() - 1 inclusive.
            int temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }

}
