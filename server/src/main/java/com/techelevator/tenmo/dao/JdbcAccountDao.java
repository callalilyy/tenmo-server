package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public class JdbcAccountDao implements AccountDao {


    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account findByAccountId(String accountId) {
        return null;
    }

    @Override
    public int findIdByUserId(String userId) {
        return 0;
    }

    @Override
    public boolean create(int userId, double balance) {
        return false;
    }
}
