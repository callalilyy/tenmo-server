package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

import java.util.List;

public interface TransferDao {




    //list of transfers with less info
    public List<TransferDTO> getTransfers(String username);

    //find transfer by transfer id
    public TransferDTO getTransfer(int transferId);

    //create transfer
    public TransferDTO create( String receiverName, Double transferAmount, String senderName);

}
