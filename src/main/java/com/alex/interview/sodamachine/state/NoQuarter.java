package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//NoQuarter.java - Represents behaviour of machine while in the NoQuarter state

@Component("noQuarter")
public class NoQuarter implements SodaMachineState {

    @Autowired
    private SodaMachine sodaMachine;

    @Override
    public Message insertCoin() {
        sodaMachine.setState(sodaMachine.getHasQuarterState());
        return new Message("Machine has quarter now");
    }

    @Override
    public Message ejectsQuarter() {
        return new Message("Sorry, machine has no quarter");
    }

    @Override
    public Message pressButton(int count) {
        return new Message("Please insert a quarter", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Message dispenseSoda(int id) {
        return new Message("Please insert a quarter");
    }
}
