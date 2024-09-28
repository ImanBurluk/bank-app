package org.example.operations.processors;

import org.example.account.Account;
import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Scanner;

public class CloseAccountProcessor implements OperationCommandProcessor {

    private final Scanner _scanner;
    private final AccountService accountService;

    public CloseAccountProcessor(Scanner _scanner, AccountService accountService) {
        this._scanner = _scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id to close:");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
