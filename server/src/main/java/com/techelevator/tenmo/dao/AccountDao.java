package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface AccountDao {

    public List<Account> findAll();

    public   Account getAccountById(int accountId);

    public int findIdByUserId(String userId);

    public  Account create(Account account);


}
