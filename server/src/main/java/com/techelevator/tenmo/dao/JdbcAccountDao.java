package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {
    @Autowired
    JdbcTemplate jdbcTemplate;




//      @Override
//    public List<Account> findAll() {
//        List<Account> allAccounts = new ArrayList<>();
//        String sql = "SELECT account_id, user_id FROM account;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
//        while(result.next()){
//            allAccounts.add(mapRowToAccount(result));
//        }
//        return allAccounts;
//    }

    @Override
    public double getBal(int userId) {
        double balance;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        balance = jdbcTemplate.queryForObject(sql, double.class, userId);
        return balance;

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
    public Account getAccountByUserId(String userId) {
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if(result.next()){
           return mapRowToAccount(result);
        }
        return null;

    }


    @Override
    public boolean create(int userId) {
        String sql = "INSERT INTO account (user_id, balance) VALUES (?,?);";
         jdbcTemplate.update(sql, userId, 1000);
        return true;
    }

    @Override
    public boolean update(Account updatedAccount, int userId) {
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, updatedAccount.getBalance(), userId) ==1;
    }

    @Override
    public void delete(int accountId) {

    }


    private Account mapRowToAccount(SqlRowSet row){
        Account account = new Account();

        account.setAccountId(row.getInt("account_id"));
        account.setUserId(row.getInt("user_id"));
        account.setBalance(row.getDouble("balance"));

        return account;
    }




}
