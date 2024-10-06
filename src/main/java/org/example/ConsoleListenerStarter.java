package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.Console;

@Component
public class ConsoleListenerStarter {


    private final OperationConsoleListener consoleListener;
    private Thread consolelistenerThread;

    public ConsoleListenerStarter(OperationConsoleListener consoleListener) {
        this.consoleListener = consoleListener;
    }
    @PostConstruct
    public void postConstruct() {
        this.consolelistenerThread = new Thread(() -> {
           consoleListener.start();
           consoleListener.listenUpdates();
        });
        consolelistenerThread.start();
    }
    @PreDestroy
    public void preDestroy() {
        consolelistenerThread.interrupt();
        consoleListener.endListen();
    }

}
