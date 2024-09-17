package org.example;

import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;

    public OperationConsoleListener(Scanner scanner) {
        this.scanner = scanner;
    }

    public void listenUpdates (){
        System.out.println("Please type operations:\n");
        while(true){
            System.out.println("Please type next operations:\n");
            String nextOperation = scanner.nextLine();
            if(nextOperation.equals("USER_CREATE")){
                System.out.println("User created\n");
            } else if(nextOperation.equals("ACCOUNT_CREATE")){
                System.out.println("Account created\n");
            } else {
                System.out.println("Not found operation\n");
            }
        }
    }
}
