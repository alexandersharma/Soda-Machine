package com.alex.interview.sodamachine;

import com.alex.interview.sodamachine.entity.Soda;
import com.alex.interview.sodamachine.service.InventoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImplTests {

    @Autowired
    InventoryService inventoryService;

    @Before
    public void init(){
        //Delete all data from h2 so each test can insert what it needs
        List<Soda> sodaList = inventoryService.findAll();
        for(Soda s: sodaList){
            s.setCount(0);
            inventoryService.save(s);
        }
    }

    @Test
    public void shouldFindById(){
        Soda soda = inventoryService.findById(1);
        assertEquals(1, soda.getId());
    }

    @Test
    public void shouldFindAll(){

        List<Soda> list = inventoryService.findAll();
        assertEquals(3, list.size());
    }

    @Test
    public void shouldSave(){
        Soda soda = inventoryService.findById(1);
        soda.setCount(3);
        inventoryService.save(soda);
        soda = inventoryService.findById(1);

        assertEquals(3, soda.getCount());
    }

    @Test
    public void shouldGetCorrectTotal(){
        int total = inventoryService.totalCount();

        assertEquals(0, total);
    }

}
