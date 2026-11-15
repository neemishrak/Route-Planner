package com.neeraj.routeplanner;

import java.util.HashMap;
import java.util.Map;

public class LocationCatalog {
    private final Map<AddressKey, Location> locationsByAddress = new HashMap<>();

    /*Method to find or Create - including building the addressKey*/
    public Location findOrCreate(Address address, String name){
        AddressKey addressKey = new AddressKey(address.streetAddress(),
                address.secondaryAddress(), address.city(),address.state(), address.zipCode());
        Location existingLocation = locationsByAddress.get(addressKey);
        if (existingLocation != null) {
            return existingLocation;
        }
        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        if (!locationsByAddress.containsKey(addressKey)) {
            locationsByAddress.put(addressKey,location);
        }
        return location;
    }
}