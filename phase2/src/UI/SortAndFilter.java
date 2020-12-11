package UI;

public class SortAndFilter {
    public SortAndFilter (){};

    public void SortHelper(){
        System.out.println("Sorting option: ");
        System.out.println("1. Sort by eventId.");
        System.out.println("2. Sort by roomId.");
        System.out.println("3. Sort by speaker's username.");
        System.out.println("4. Sort by starting time of event.");
        System.out.println("5. Sort by title of event.");
        System.out.println("Please choose one of the options. For example, enter eventId, if you want to choose the " +
                "first option. (Enter 'exit' to skip and finish.)");
    }

    public void FilterHelper(){
        System.out.println("Filter option: ");
        System.out.println("1. Filter by speaker of event.");
        System.out.println("2. Filter by starting time of event.");
        System.out.println("3. Filter by date.");
        System.out.println("Please choose options you want one at a time. (Enter the number first then press Enter " +
                "then type in the restriction for the selected filter.");
    }
}
