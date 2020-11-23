package Entity;

import java.util.ArrayList;

/**
 * The entity class for Organizer, a child class of User
 *
 * This class extends the {@link User} parent class and provides
 * user a toString method to tell it from other type of user.
 */

public class Organizer extends User {
    public Organizer(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "Organizer";}
}
