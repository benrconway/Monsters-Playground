package com.example.user.fantasyzooapp;

import com.example.user.fantasyzooapp.facilities.Zoo;
import com.example.user.fantasyzooapp.outsourcing.Breeder;
import com.example.user.fantasyzooapp.outsourcing.Construction;
import com.example.user.fantasyzooapp.outsourcing.FoodSupplier;
import com.example.user.fantasyzooapp.outsourcing.RecruitmentAgency;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 25/09/2017.
 */

public class ZooTest {

    Zoo zoo;
    Breeder breeder;
    Construction contractor;
    RecruitmentAgency agencyRep;
    FoodSupplier foodSupplier;

    @Before
    public void before() {
        zoo = new Zoo();
        breeder = new Breeder();
        contractor = new Construction();
        agencyRep = new RecruitmentAgency();
        foodSupplier = new FoodSupplier();

    }

    @Test
    public void canRetrieveDetails() {
        assertEquals(false, zoo.areTheGatesOpen());
        assertEquals(100, zoo.getCustomerCapacity());
        assertEquals(10, zoo.getTicketPrice());
        assertEquals(1_000_000, zoo.getFunds());
        assertEquals(0, zoo.getDay());
        assertEquals(0, zoo.getRoaming().size());
        assertEquals(0, zoo.getMeatStock().size());
        assertEquals(0, zoo.getLoose().size());
        assertEquals(0, zoo.getStructures().size());
        assertEquals(0, zoo.getVegetableStock().size());
    }

    @Test
    public void zooCanBuyFromBreeder() {
        //Buying a Huge Dragon which costs 30,000
        zoo.buyAnimalByIndex(0, breeder);
        assertEquals(1, zoo.getOutBack().size());
        assertEquals(40_000, breeder.getSavings());
        assertEquals(970_000, zoo.getFunds());
    }

    @Test
    public void zooCanBuyInBuilding() {
        //Buying a huge environment for the dragon
        zoo.addStructureByIndex(0, contractor);
        assertEquals(1, zoo.getStructures().size());
        assertEquals(970_000, zoo.getFunds());

    }

    @Test
    public void zooCanHireStaff() {
        zoo.hireStaff(agencyRep);
        assertEquals(1, zoo.getHired().size());
        assertEquals(999_700, zoo.getFunds());
    }

    @Test
    public void canBuyInFoodStocks() {
        zoo.restockVegetables(10, foodSupplier);
        zoo.restockMeat(20, foodSupplier);
        assertEquals(10, zoo.getVegetableStock().size());
        assertEquals(20, zoo.getMeatStock().size());
    }


}
