package ru.unecon;

import org.junit.Test;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void testMessageRecord() {
        Message message = new Message(42);
        assertEquals(42, message.info());
    }

    @Test
    public void testFilterAcceptsValidMessages() throws Exception {
        Logger logger = new Logger();
        BlockingQueue<Message> input = new LinkedBlockingQueue<>();
        BlockingQueue<Message> output = new LinkedBlockingQueue<>();
        Filter filter = new Filter(input, output, logger);
        
        filter.start();

        input.put(new Message(4));
        input.put(new Message(0));
        input.put(new Message(100));

        Thread.sleep(200);

        assertEquals(4, output.take().info());
        assertEquals(0, output.take().info());
        assertEquals(100, output.take().info());
        
        filter.shutdown();
    }

    @Test
    public void testFilterRejectsInvalidMessages() throws Exception {
        Logger logger = new Logger();
        BlockingQueue<Message> input = new LinkedBlockingQueue<>();
        BlockingQueue<Message> output = new LinkedBlockingQueue<>();
        Filter filter = new Filter(input, output, logger);
        
        filter.start();

        input.put(new Message(-2));
        input.put(new Message(3));
        input.put(new Message(-1));

        Thread.sleep(200);

        assertTrue(output.isEmpty());
        
        filter.shutdown();
    }
}