package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * SodaMachine.java - Maintains the current state of the soda machine
 * State is managed using a State Design Pattern
 * This class acts as the context component of the pattern
 */


@Component
public class SodaMachine{
    @Autowired
    private SodaMachineState noQuarter;

    @Autowired
    private SodaMachineState hasQuarter;

    @Autowired
    private SodaMachineState sold;

    @Autowired
    private SodaMachineState soldOut;

    @Autowired
    @Qualifier("noQuarter")
    private SodaMachineState state;

    public Message insertCoin() {
        return state.insertCoin();
    }

    public Message ejectQuarter() {
        return state.ejectsQuarter();
    }

    public Message pressButton(int count) {
        return state.pressButton(count);
    }

    public Message dispenseSoda(int id){
        return state.dispenseSoda(id);
    }

    public SodaMachineState getNoQuarterState() {
        return noQuarter;
    }

    public SodaMachineState getHasQuarterState() {
        return hasQuarter;
    }

    public SodaMachineState getSoldState() {
        return sold;
    }

    public SodaMachineState getSoldOutState() {
        return soldOut;
    }

    public void setState(SodaMachineState newState) {
        this.state = newState;
    }

    public SodaMachineState getState(){ return this.state; }
}

