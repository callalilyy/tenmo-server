package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao {
    JdbcTemplate jdbcTemplate;






    @Override
    public double getBalance(int accountid) {
        String sql = "SELECT balance FROM account WHERE account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountid);

        return 0;

    }

    @Override
    public List<Account> findAll() {
        List<Account> allAccounts = new ArrayList<>();
        return null;
    }

    @Override
    public Account getAccountById(int accountId) {

        String sql = "SELECT account_id, user_id, balance FROM account WHERE account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        if(result.next()){
            return mapRowToAccount(result);
        }
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


    private Account mapRowToAccount(SqlRowSet row){
        Account account = new Account();

        account.setAccountId(row.getInt("account_id"));
        account.setUserId(row.getInt("user_id"));
        account.setBalance(row.getDouble("balance"));

        return account;
    }




}
