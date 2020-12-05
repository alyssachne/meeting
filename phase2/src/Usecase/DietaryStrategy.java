package Usecase;

import Entity.DietaryRequest;
import Entity.SpecialRequests;
import Entity.User;

import static Usecase.Act.*;

public class DietaryStrategy implements RequestAddressingStrategies{

    @Override
    public void address(SpecialRequests requests) {
        //todo
        requests.address();

    }
}
