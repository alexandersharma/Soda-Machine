package com.alex.interview.sodamachine;

import com.alex.interview.sodamachine.entity.Soda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SodaTests {

    @Test
    public void shouldCreateSodaAndSetId(){
        Soda soda = new Soda();
        soda.setId(1);

        assertEquals(1, soda.getId());
    }

    @Test
    public void shouldCreateSodaAndSetCount(){
        Soda soda = new Soda();
        soda.setCount(3);

        assertEquals(3, soda.getCount());
    }


    @Test
    public void shouldCreateSodaAndSetName(){
        Soda soda = new Soda();
        soda.setName("Coke");

        assertEquals("Coke", soda.getName());
    }
}
