package ru.unecon;

import java.util.concurrent.BlockingQueue;

public class Filter extends Thread {
    private final BlockingQueue<Message> inputQueue;
    private final BlockingQueue<Message> outputQueue;
    private final Logger logger;
    private volatile boolean running = true;

    public Filter(BlockingQueue<Message> inputQueue, 
                  BlockingQueue<Message> outputQueue, 
                  Logger logger) {
        super("Filter");
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Message message = inputQueue.take();
                int info = message.info();
                
                if (info >= 0 && info % 2 == 0) {
                    logger.log(getName(), "message delivered " + info);
                    outputQueue.put(message);
                } else {
                    logger.log(getName(), "message skipped " + info);
                }
            }
        } catch (InterruptedException e) {
        }
    }

    public void shutdown() {
        running = false;
        interrupt();
    }
}