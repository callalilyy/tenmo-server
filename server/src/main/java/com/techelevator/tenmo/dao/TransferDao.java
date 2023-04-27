package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> findAll();

    Transfer findByTransferId(String transferId);

    Transfer findBySenderId(int senderId);
    Transfer findByReceiverId(int receiverId);

    int findTransferIdById(int id);

    boolean create(int userId, double balance);

}
