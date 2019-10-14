package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;

public interface SodaMachineState {

    Message insertCoin();

    Message ejectsQuarter();

    Message pressButton(int count);

    Message dispenseSoda(int id);
}
