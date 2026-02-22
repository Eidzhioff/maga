package ru.unecon;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Generator extends Thread {
    private final BlockingQueue<Message> outputQueue;
    private final Logger logger;
    private final Random random = new Random();
    private volatile boolean running = true;

    public Generator(BlockingQueue<Message> outputQueue, Logger logger) {
        super("Generator");
        this.outputQueue = outputQueue;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (running) {
                // Случайная задержка 1-5 секунд
                Thread.sleep(1000 + random.nextInt(4000));
                
                Message message = new Message(random.nextInt());
                logger.log(getName(), "message generated " + message.info());
                outputQueue.put(message);
            }
        } catch (InterruptedException e) {
        }
    }

    public void shutdown() {
        running = false;
        interrupt();
    }
}