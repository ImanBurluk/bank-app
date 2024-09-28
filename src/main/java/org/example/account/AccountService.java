package org.example.account;

import org.example.user.User;

import java.util.*;

public class AccountService {

    private final Map<Integer, Account> accountrMap;

    private int idCounter;

    public AccountService() {
        this.accountrMap = new HashMap<>();
        this.idCounter = 0;
    }

    public Account createAccount(User user) {
        idCounter++;
        Account account = new Account(idCounter, user.getId(), 0);// todo default amount
        accountrMap.put(account.getId(), account);
        return account;
    }

    public Optional<Account> findAccountById(int id) {
        return Optional.ofNullable(accountrMap.get(id));
    }

    public List<Account> getAllUserAccounts(int userId) {
        return accountrMap.values()
                .stream()
                .filter(account -> account.getUserId() == userId)
                .toList();
    }

    public void depositAccount(int accountId, int moneyToDeposit) {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s".formatted(accountId)));
        if (moneyToDeposit <= 0) {
            throw new IllegalArgumentException("Cannot deposit not positive amount: amount=%s"
                    .formatted(moneyToDeposit));
        }

        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
    }

    public void withdrawFromAccount(int accountId, int amountToWithdraw) {
            var account = findAccountById(accountId)
                    .orElseThrow(() -> new IllegalArgumentException("No such account: id=%s".formatted(accountId)));

            if (amountToWithdraw <= 0) {
                throw new IllegalArgumentException("Cannot withdraw not positive amount: amount=%s"
                        .formatted(amountToWithdraw));
            }

            if (account.getMoneyAmount() < amountToWithdraw) {
                throw new IllegalArgumentException("Cannot withdraw from account: id=%s, moneyAmount=%s, attemptedWithdraw=%s"
                        .formatted(accountId, account.getMoneyAmount(), amountToWithdraw));
            }

            account.setMoneyAmount(account.getMoneyAmount() - amountToWithdraw);

    }
}
