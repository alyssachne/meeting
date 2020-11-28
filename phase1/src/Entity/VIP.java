package Entity;

import Usecase.VipAct;

/**
 * The entity class for VIP, a child class of User
 *
 * This class extends the {@link User} parent class and provides
 * user a method to tell it from other type of user.
 * The toString method will allows the signup method in the {@link VipAct} to identify the user as vip and allows
 * enrollment to Vip exclusive events
 */

public class VIP extends User{

    public VIP(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public String typeGetter(){return "VIP";}
}