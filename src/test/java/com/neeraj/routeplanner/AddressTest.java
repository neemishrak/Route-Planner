package com.neeraj.routeplanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void addressTest(){
        //Arrange
        Address address = new Address(" 100\tMain   St ",
                "apt   23", "new york","ma", "02108",null);
        //Assert
        assertEquals("APT 23",address.secondaryAddress());
        assertEquals("100 MAIN ST",address.streetAddress());
        assertEquals("NEW YORK",address.city());
        assertEquals("MA",address.state());
        assertEquals("02108", address.zipCode());
        assertNull(address.zipPlus4());


    }
    @Test
    void cityTest(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Address("100 Main St",
                        null, "   ", "MA", "02108", null)
        );
        assertEquals("City is required", exception.getMessage());
    }
    @Test
    void zipCodeTest(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Address("100 Main St",
                        null, "Boston", "MA", "0 2108", null)
        );
        assertEquals("Zip Code Invalid", exception.getMessage());
    }
    @Test
    void zipPlus4Test(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Address("100 Main St",
                        null, "Boston", "MA", "02108", "null")
        );
        assertEquals("invalid ZipPlus4", exception.getMessage());
    }
}
