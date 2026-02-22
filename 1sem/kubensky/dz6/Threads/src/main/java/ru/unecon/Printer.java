package ru.unecon;

import java.util.concurrent.BlockingQueue;

public class Printer extends Thread {
    private final BlockingQueue<Message> inputQueue;
    private final Logger logger;
    private volatile boolean running = true;

    public Printer(BlockingQueue<Message> inputQueue, Logger logger) {
        super("Printer");
        this.inputQueue = inputQueue;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Message message = inputQueue.take();
                logger.log(getName(), "Printing a message " + message.info());
            }
        } catch (InterruptedException e) {
            // Завершение работы потока
        }
    }

    public void shutdown() {
        running = false;
        interrupt();
    }
}