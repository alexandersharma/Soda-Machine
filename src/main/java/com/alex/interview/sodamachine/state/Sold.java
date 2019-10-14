package com.alex.interview.sodamachine.state;

import com.alex.interview.sodamachine.entity.Message;
import com.alex.interview.sodamachine.entity.Soda;
import com.alex.interview.sodamachine.service.InventoryService;
import com.alex.interview.sodamachine.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//Sold.java - Represents behaviour of machine while in the Sold state

@Component
public class Sold implements SodaMachineState {

    private static final Path REPORT = Paths.get("src/main/resources/report.txt");

    @Autowired
    private SodaMachine sodaMachine;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ReportingService reportingService;

    @Override
    public Message insertCoin() {
        return new Message("Machine is down because of reporting issues");
    }

    @Override
    public Message ejectsQuarter() {
        return new Message("Machine is down because of reporting issues");
    }

    @Override
    public Message pressButton(int count) {
        return new Message("Machine is down because of reporting issues", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Message dispenseSoda(int id) {
        //Get requested soda
        Soda soda = inventoryService.findById(id);

        //Do reporting
        try{
            reportingService.doReporting(soda, REPORT);
        }catch(IOException e) {
            return new Message("Machine is down because of reporting issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Update the count
        soda.setCount(soda.getCount()-1);
        inventoryService.save(soda);

        //If machine is totally empty set state to sold out
        //If it still has some sodas set state to no quarter
        if(inventoryService.totalCount() == 0) {
            sodaMachine.setState(sodaMachine.getSoldOutState());
        }else {
            sodaMachine.setState(sodaMachine.getNoQuarterState());
        }

        return new Message("Here is your " + soda.getName());
    }
}
