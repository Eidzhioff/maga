package ru.unecon;

import java.time.Duration;
import java.time.Instant;

public class Logger {
    private final Instant startTime = Instant.now();

    public void log(String threadName, String action) {
        long elapsedSeconds = Duration.between(startTime, Instant.now()).getSeconds();
        System.out.println(threadName + " at " + elapsedSeconds + ": " + action);
    }
}