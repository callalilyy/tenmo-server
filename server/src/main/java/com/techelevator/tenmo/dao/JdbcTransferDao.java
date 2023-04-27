package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public class JdbcTransferDao implements TransferDao {


    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Transfer findByTransferId(String transferId) {
        return null;
    }

    @Override
    public Transfer findBySenderId(int senderId) {
        return null;
    }

    @Override
    public Transfer findByReceiverId(int receiverId) {
        return null;
    }

    @Override
    public int findTransferIdById(int id) {
        return 0;
    }

    @Override
    public boolean create(int userId, double balance) {
        return false;
    }
}
