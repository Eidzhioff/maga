package ru.unecon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageProcessor {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Message> genToFilter = new LinkedBlockingQueue<>();
        BlockingQueue<Message> filterToPrint = new LinkedBlockingQueue<>();
        Logger logger = new Logger();

        Generator generator = new Generator(genToFilter, logger);
        Filter filter = new Filter(genToFilter, filterToPrint, logger);
        Printer printer = new Printer(filterToPrint, logger);

        generator.start();
        filter.start();
        printer.start();

        Thread.sleep(60000);

        generator.shutdown();
        filter.shutdown();
        printer.shutdown();

        generator.join(1000);
        filter.join(1000);
        printer.join(1000);
    }
}