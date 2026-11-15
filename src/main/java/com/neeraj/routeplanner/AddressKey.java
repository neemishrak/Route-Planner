package com.neeraj.routeplanner;

public record AddressKey(String streetAddress, String secondaryAddress, String city,
                         String state, String zipCode) {
}