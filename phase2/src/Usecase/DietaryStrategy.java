package Usecase;

import Entity.SpecialRequests;

public class DietaryStrategy implements RequestAddressingStrategies{

    @Override
    public void address(SpecialRequests requests) {
        requests.address();

    }
}
