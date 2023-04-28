package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    public List<Transfer> findAll();

    public Transfer findByTransferId(String transferId);

   public  Transfer findBySenderId(int senderId);
    public Transfer findByReceiverId(int receiverId);

    public int findTransferIdById(int id);

    public Transfer create(Transfer transfer);

}
