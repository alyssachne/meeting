package Controller.Sorter;

import Usecase.EventFactory;

import java.util.ArrayList;

public class EventTimeSorter implements SorterStrategy {


    @Override
    // Selection Sort
    public void sort(ArrayList<Integer> tl, EventFactory ef) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the earliest time in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (ef.getEvent(tl.get(indexMin)).getDate().after(ef.getEvent(tl.get(j)).getDate())) {
                    indexMin = j;
                } else if (ef.getEvent(tl.get(indexMin)).getDate() == (ef.getEvent(tl.get(j)).getDate())) {
                    if (ef.getEvent(tl.get(indexMin)).getTime() > ef.getEvent(tl.get(j)).getTime()) {
                        indexMin = j;
                    }
                }
            }

            // Swap the item at index i with the item with the earliest time
            // between i and  list.size() - 1 inclusive.
            int temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
