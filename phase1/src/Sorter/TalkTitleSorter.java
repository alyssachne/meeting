package Sorter;

import Entity.Talk;

import java.util.ArrayList;

public class TalkTitleSorter implements Sorter {


    public TalkTitleSorter(){}


    @Override
    // Selection Sort
    public void sort(ArrayList<Talk> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the talk with title alphabetically in the beginning of the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (tl.get(indexMin).title.compareTo(tl.get(j).title) > 0) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the talk with title alphabetically in the beginning of the list
            // between i and  list.size() - 1 inclusive.
            Talk temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
