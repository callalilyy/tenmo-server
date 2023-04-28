package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao {
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Account> findAll() {
        List<Account> allAccounts = new ArrayList<>();

        return null;
    }

    @Override
    public Account getAccountById(int accountId) {
        return null;
    }

    @Override
    public int findIdByUserId(String userId) {
        return 0;
    }

    @Override
    public Account create(Account account) {
        String sql = "INSERT INTO account (user_id, balance) VALUES (?,?) RETURNING account_id;";
        int accountId = jdbcTemplate.queryForObject (sql, int.class, account.getUserId(), account.getBalance());
        return getAccountById(accountId);
    }
}
