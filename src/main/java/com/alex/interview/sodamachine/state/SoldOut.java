package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//SoldOut.java - Represents behaviour of machine while in the SoldOut state

@Component
public class SoldOut implements SodaMachineState {

    @Override
    public Message insertCoin() {
        return new Message("Sorry machine is sold out");
    }

    @Override
    public Message ejectsQuarter() {
        return new Message("Sorry machine is sold out");
    }

    @Override
    public Message pressButton(int count) {
        return new Message("Sorry machine is sold out", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Message dispenseSoda(int id) {
        return new Message("Sorry machine is sold out");
    }
}
