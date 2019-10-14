package com.alex.interview.sodamachine.rest;

import com.alex.interview.sodamachine.entity.Message;
import com.alex.interview.sodamachine.entity.Soda;
import com.alex.interview.sodamachine.service.InventoryService;
import com.alex.interview.sodamachine.state.SodaMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sodaMachine")
public class SodaController {

    @Autowired
    private SodaMachine sodaMachine;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/insertCoin")
    public Message insertCoin(){
        return sodaMachine.insertCoin();
    }

    @GetMapping("/requestRefund")
    public Message requestRefund(){
        return sodaMachine.ejectQuarter();
    }

    @GetMapping("/pressButton/{count}")
    public ResponseEntity<Message> pressButton(@PathVariable int count){
        Message message = sodaMachine.pressButton(count);
        HttpStatus status = getStatus(message);

        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/dispenseSoda/{id}")
    public ResponseEntity<Message> dispenseSoda(@PathVariable int id){
        Message message = sodaMachine.dispenseSoda(id);
        HttpStatus status = getStatus(message);

        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/inventory")
    public List<Soda> inventory(){
        List<Soda> sodas = inventoryService.findAll();

        return sodas;
    }

    @GetMapping("/totalCount")
    public int totalCount(){
        return inventoryService.totalCount();
    }

    private HttpStatus getStatus(Message message){
        HttpStatus status = HttpStatus.OK;
        if(message.getStatus() != null){
            status = message.getStatus();
        }
        return status;
    }
}
