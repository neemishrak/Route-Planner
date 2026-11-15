package com.neeraj.routeplanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationCatalogTest {

    @Test
    void locationCatalogTest(){

        //Arrange//
        LocationCatalog locationCatalog = new LocationCatalog();
        Address firstAddress = new Address("127 talmage Ave", "312", "Bound Brook"
                , "NJ", "08805", "0129");

        String name = "SuppliesShop";
        Location first = locationCatalog.findOrCreate(firstAddress,name);
        Address secondAddress = new Address("127 talmage Ave", "312", "Bound Brook"
                , "NJ", "08805", null);
        Location second = locationCatalog.findOrCreate(secondAddress, name);

        //Assertion//

        assertSame(first, second);
        assertEquals("SuppliesShop", second.getName());

    }

    @Test
    void DifferentUnitsTest(){
        LocationCatalog locationCatalog = new LocationCatalog();
        Address firstAddress = new Address("127 talmage Ave", "312", "Bound Brook"
                , "NJ", "08805", "0129");

        String firstName = "PickUpShop";
        Location first = locationCatalog.findOrCreate(firstAddress,firstName);
        Address secondAddress = new Address("127 talmage Ave", "313", "Bound Brook"
                , "NJ", "08805", null);
        String secondName = "PaintShop";
        Location second = locationCatalog.findOrCreate(secondAddress, secondName);
        assertNotSame(first,second);
        assertNotEquals("PickUpShop",second.getName() );
    }
}
