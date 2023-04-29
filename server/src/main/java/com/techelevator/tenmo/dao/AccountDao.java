package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface AccountDao {


    //find list of all accounts
//    public List<Account> findAll();

    public double getBalance(int accountid);
    //find account using account id
    public Account getAccountById(int accountId);

    //DON'T THINK WE NEED THIS
    public Account getAccountByUserId(String userId);

    //create account using account id
    public Account create(Account account);


}
