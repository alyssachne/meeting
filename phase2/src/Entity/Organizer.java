package Entity;



/**
 * The entity class for Organizer, a child class of User
 *
 * This class extends the {@link User} parent class and provides
 * user a toString method to tell it from other type of user.
 */

public class Organizer extends User{
    public Organizer(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "Organizer";}

//    /**
//     * This method is called whenever the observed object is changed. An
//     * application calls an <tt>Observable</tt> object's
//     * <code>notifyObservers</code> method to have all the object's
//     * observers notified of the change.
//     *
//     * @param o   the observable object.
//     * @param arg an argument passed to the <code>notifyObservers</code>
//     */
//
//    @Override
//    public void update(Observable o, Object arg) {
//        System.out.println("This special request is " + arg);
//    }
}
