package Usecase;

import java.util.HashMap;
import Entity.*;

public class SpecialRequestManager {
    public static HashMap<Integer,SpecialRequests> specialRequestsHashMap;

    /**
     * Initialize a SpecialRequestManager
     */
    public SpecialRequestManager(){specialRequestsHashMap = new HashMap<>();}

    public void createSpecialRequest(String description, String userName, String type){
        if (type.equals("Accessibility Request")){
            AccessibilityRequest newRequest = new AccessibilityRequest(description,userName);
            specialRequestsHashMap.put(newRequest.getRequestId(),newRequest);
        }
        else {
            DietaryRequest newRequest = new DietaryRequest(description,userName);
            specialRequestsHashMap.put(newRequest.getRequestId(),newRequest);
        }
    }

    /**
     * The UseCase Method to address a request, this method is based on strategy design pattern
     * @param request: the special request to be addressed here.
     * @param strategy: the strategy that we will use to address this request.
     */
    public void addressSpecialRequest(SpecialRequests request, RequestAddressingStrategies strategy){
        strategy.address(request);
    }
}
