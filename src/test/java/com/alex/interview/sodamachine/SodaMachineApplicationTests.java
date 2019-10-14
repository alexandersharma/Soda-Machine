package com.alex.interview.sodamachine;

import com.alex.interview.sodamachine.entity.Soda;
import com.alex.interview.sodamachine.service.InventoryService;
import com.alex.interview.sodamachine.state.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SodaMachineApplicationTests {

    @Autowired
    SodaMachine sodaMachine;

    @Autowired
    InventoryService inventoryService;

    @Before
    public void init(){
        //Set state of soda machine to NoQuarter before each test
        sodaMachine.setState(sodaMachine.getNoQuarterState());

        //Delete all data from h2 so each test can insert what it needs
        List<Soda> sodaList = inventoryService.findAll();
        for(Soda s: sodaList){
            s.setCount(0);
            inventoryService.save(s);
        }

    }

    @Test
    public void shouldBeInHasQuarterStateWhenQuarterInserted(){
        sodaMachine.insertCoin();
        SodaMachineState state = sodaMachine.getState();
        assertTrue(state instanceof HasQuarter);
    }

    @Test
    public void shouldBeInSoldStateWhenQuarterIsInsertedAndButtonPressed(){
        sodaMachine.insertCoin();
        sodaMachine.pressButton(2);
        SodaMachineState state = sodaMachine.getState();
        assertTrue(state instanceof Sold);
    }

    @Test
    public void shouldDispenseSodaWhenInSoldState(){
        //Add a soda to dispense
        Soda soda = getSoda(1);
        soda.setCount(1);
        inventoryService.save(soda);

        //Purchase soda
        sodaMachine.insertCoin();
        sodaMachine.pressButton(1);
        sodaMachine.dispenseSoda(1);

        //Check that count decreased by one
        int actual = getSodaCount(1);
        int expected = 0;
        assertEquals(expected, actual);

    }

    @Test
    public void shouldSetStateToSoldOutWhenNoSodasLeft(){
        //Set soda count equal to one
        Soda soda = getSoda(1);
        soda.setCount(1);
        inventoryService.save(soda);

        //Purchase the one soda in the machine
        sodaMachine.insertCoin();
        sodaMachine.pressButton(1);
        sodaMachine.dispenseSoda(1);

        //Check that state is Sold Out
        SodaMachineState state = sodaMachine.getState();
        assertTrue(state instanceof SoldOut);
    }

    @Test
    public void shouldSetStateToNoQuarterWhenSodasAreLeft(){
        //Set soda count equal to one
        Soda soda = getSoda(1);
        soda.setCount(2);
        inventoryService.save(soda);

        //Purchase the one soda in the machine
        sodaMachine.insertCoin();
        sodaMachine.pressButton(2);
        sodaMachine.dispenseSoda(1);

        //Check that state is No Quarter
        SodaMachineState state = sodaMachine.getState();
        assertTrue(state instanceof NoQuarter);
    }

    @Test
    public void shouldEjectQuarterWhenRefundButtonIsPressedAndInHasQuarterState(){
        sodaMachine.insertCoin();
        sodaMachine.ejectQuarter();

        //Check that state is back to No Quarter
        SodaMachineState state = sodaMachine.getState();
        assertTrue(state instanceof NoQuarter);
    }

    private Soda getSoda(int id){
        return inventoryService.findById(1);
    }
    private int getSodaCount(int id){
        Soda soda = inventoryService.findById(id);
        return soda.getCount();
    }
}
