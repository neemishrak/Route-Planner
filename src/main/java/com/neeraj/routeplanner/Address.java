package com.neeraj.routeplanner;


import java.util.Locale;


public record  Address(String streetAddress, String secondaryAddress, String city,
                       String state, String zipCode, String zipPlus4){

        public Address {

            /*Check State*/
            if (state == null || state.isBlank()) {
                throw new IllegalArgumentException("State is required");
            }

            state = state.trim().toUpperCase(Locale.ROOT);

            if (!state.matches("[A-Z]{2}")) {
                throw new IllegalArgumentException("State must be a two-letter code");
            }

            /* Secondary address can be blank*/
            if (secondaryAddress == null || secondaryAddress.isBlank()) {
                secondaryAddress = null;
            } else {
                secondaryAddress = secondaryAddress.trim()
                        .replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);

            }
            /*Street Address Validation*/
            if (streetAddress == null || streetAddress.isBlank()) {
                throw new IllegalArgumentException("Street Address is required");
            }

           streetAddress = streetAddress.trim().replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);

            /* City Checks*/
            if (city == null || city.isBlank()) {
                throw new IllegalArgumentException("City is required");
            }

            city = city.trim().replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);

            /*ZipCode Validation*/
            if (zipCode == null || zipCode.isBlank()) throw new IllegalArgumentException("ZipCode cannot be Blank");
            zipCode = zipCode.trim();
            if (!zipCode.matches("[0-9]{5}")) throw new IllegalArgumentException("Zip Code Invalid");

            /*ZipPlus4 Validation*/
            if (zipPlus4 == null || zipPlus4.isBlank()){
                zipPlus4 = null;
            } else {
                zipPlus4 = zipPlus4.trim();
            }
            if (!(zipPlus4 == null) && (!zipPlus4.matches("[0-9]{4}")))
                throw new IllegalArgumentException("invalid ZipPlus4");

        }
}


