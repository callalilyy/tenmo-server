package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

import java.util.List;

public interface TransferDao {

    //list all transfers with all details
    public List<Transfer> getDetailedTransfers(String username);

    //list of transfers with less info
    public List<TransferDTO> getTransfers(String username);

    //find transfer by transfer id
    public Transfer findByTransferId(int transferId);


    //create transfer
    public Transfer create(Transfer transfer);

}
