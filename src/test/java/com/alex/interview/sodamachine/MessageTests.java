package com.alex.interview.sodamachine;

import com.alex.interview.sodamachine.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTests {

    @Test
    public void shouldCreateMessageAndSetDetails(){
        Message message = new Message("Hello");

        assertEquals("Hello", message.getDetails());
    }

    @Test
    public void shouldCreateMessageAndSetStatusCode(){
        Message message = new Message("Hello", HttpStatus.OK);

        assertEquals(HttpStatus.OK, message.getStatus());
    }
}
