//package Entity;
//
//import Usecase.OrganizerAct;
//
//import java.util.Collection;
//import java.util.Observable;
//
//public abstract class SpecialRequests extends Observable {
//    private boolean addressed;
//    private int requestId;
//    private String description;
//    protected String type;
//    private String username;
//    private static int numberOfRequest;
//
//
//    /** An abstract constructor for the specialRequest class
//     *
//     * @param description: The description of the request submitted by the user
//     * @param username: The userName of the User that submitted the request
//     *
//     * The constructor will constructor a request that has Id equal to the preexisting number of requests plus one and
//     *                have the address status set as not addresses.
//     */
//
//    public SpecialRequests(String description,String username) {
//        this.addressed = false;
//        this.requestId = numberOfRequest +1;
//        this.description = description;
//        this.username = username;
//        Collection<Organizer> existingOrganizerList = OrganizerAct.organizerMap.values();
//        for (Organizer organizer: existingOrganizerList){
//            this.addObserver(organizer);
//        }
//        numberOfRequest += 1;
//        this.setChanged();
//        this.notifyObservers(" still pending.");
//    }
//
//    // The method to address this Request, set the addressing status to true
//    public void address(){
//        this.addressed= true;
//        this.setChanged();
//        this.setChanged();
//        this.notifyObservers(" successfully addressed!");
//    }
//
//    public int getRequestId() {
//        return requestId;
//    }
//
//    public String getUsername(){
//        return this.username;
//    }
//
//    public String getDescription(){
//        return this.description;
//    }
//
//}
