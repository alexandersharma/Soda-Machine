package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//HasQuarter.java - Represents behaviour of machine while in the HasQuarter state

@Component
public class HasQuarter implements SodaMachineState {

    @Autowired
    private SodaMachine sodaMachine;

    @Override
    public Message insertCoin() {
        return new Message("Machine already has a quarter");
    }

    @Override
    public Message ejectsQuarter() {
        sodaMachine.setState(sodaMachine.getNoQuarterState());
        return new Message("Here is your quarter back");

    }

    @Override
    public Message pressButton(int count) {
        //If the specific kind of soda requested is sold out return sold out message
        if(count == 0){
            return new Message ("This kind of soda is sold out", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sodaMachine.setState(sodaMachine.getSoldState());
        return new Message("Button is pressed");
    }

    @Override
    public Message dispenseSoda(int id) {
        return new Message("Please make a selection");
    }
}
