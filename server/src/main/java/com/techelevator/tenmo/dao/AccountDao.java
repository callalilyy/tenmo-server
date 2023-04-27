package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    Account findByAccountId(String accountId);

    int findIdByUserId(String userId);

    boolean create(int userId, double balance);


}
