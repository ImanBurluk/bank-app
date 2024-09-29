package org.example;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.operations.processors.AccountTransferProcessor;
import org.example.operations.processors.CreateAccountProcessor;
import org.example.operations.processors.CreateUserProcessor;
import org.example.operations.processors.ShowAllUsersProcessor;
import org.example.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            List<OperationCommandProcessor> commandProcessorList
    ){
        Map<ConsoleOperationType, OperationCommandProcessor> map =
                commandProcessorList
                        .stream()
                        .collect(
                                Collectors.toMap(OperationCommandProcessor::getOperationType, items -> items)
                        )
                ;
        return new OperationConsoleListener(scanner, map);
    }

    @Bean
    public UserService userService(
            AccountService accountService
    ){
        return new UserService(accountService);
    }

    @Bean
    public AccountService accountService(
            @Value("${account.default-amount}") int defaultAmount,
            @Value("${account.transfer-commission}") double transferCommission
    ){
        return new AccountService(defaultAmount, transferCommission);
    }

    @Bean
    public AccountTransferProcessor accountTransferProcessor(
            Scanner scanner,
            AccountService accountService
    ){
        return new AccountTransferProcessor(scanner, accountService);
    }
}
