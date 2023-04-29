package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    //list all transfers
    public List<Transfer> findAll(int userId);

    //find transfer by transfer id
    public Transfer findByTransferId(int transferId);


    //create transfer
    public Transfer create(Transfer transfer);

}
