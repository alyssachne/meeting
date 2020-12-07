package Usecase;

import Entity.SpecialRequests;

public class AccessibilityStrategies implements RequestAddressingStrategies{
    @Override
    public void address(SpecialRequests request) {
        request.address();
    }
}
