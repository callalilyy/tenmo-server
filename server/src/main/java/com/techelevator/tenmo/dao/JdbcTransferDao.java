package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<TransferDTO> getTransfers() {
        List<TransferDTO> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, sender_name, receiver_name, te_bucks FROM transfer;";             //"WHERE sender_name = ? OR receiver_name = ? ORDER BY date_created;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            transfers.add(mapRowToTransferDTO(result));

        }
        return transfers;
    }


    @Override
    public List<Transfer> getDetailedTransfers(String username) {
        List<Transfer> detailedTransfers = new ArrayList<>();
        String sql = "SELECT transfer_id, sender_id, receiver_id, te_bucks, status, date_created,sender_name, receiver_name" +
                "FROM transfer WHERE sender_name = ? OR receiver_name = ? ORDER BY date_created;";
        //NOT SURE IF USERID NEEDS TO PASSED TWICE
        //NOT SURE IF I NEED TO LIST ALL COLUMNS IN SELECT STATEMENT TO MAPROWTOTRANSFER CORRECTLY or if I can select only columns I want
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        while(result.next()){
            detailedTransfers.add(mapRowToTransfer(result));
        }
        return detailedTransfers;
    }


    @Override
    public Transfer findByTransferId(int transferId) {
        String sql = "SELECT transfer_id, sender_id, receiver_id, te_bucks, status, date_created,sender_name, receiver_name" +
        "FROM transfer WHERE transfer_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        if(result.next()){
            return mapRowToTransfer(result);
        }

        return null;
    }




    @Override
    public Transfer create(Transfer transfer) {

        String sql = "INSERT INTO transfer (sender_id, receiver_id, te_bucks, status, date_created,sender_name, receiver_name)" +
                "VALUES(?,?,?,?,?,?,?,?) RETURNING transfer_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, transfer.getSenderId(), transfer.getReceiverId(), transfer.getTeBucks(), transfer.getStatus(), transfer.getDateCreated(), transfer.getSenderName(), transfer.getReceiverName());

        return findByTransferId(newId);
    }


    private Transfer mapRowToTransfer(SqlRowSet row){
        Transfer detailedTransfer = new Transfer();
        detailedTransfer.setTransferId(row.getInt("transfer_id"));
        detailedTransfer.setSenderId(row.getInt("sender_id"));
        detailedTransfer.setReceiverId(row.getInt("receiver_id"));
        detailedTransfer.setTeBucks(row.getDouble("te_bucks"));
        detailedTransfer.setStatus(row.getString("status"));
        detailedTransfer.setDateCreated(row.getTimestamp("date_created").toLocalDateTime());
        detailedTransfer.setSenderName(row.getString("sender_name"));
        detailedTransfer.setReceiverName(row.getString("receiver_name"));

        return detailedTransfer;
    }


    private TransferDTO mapRowToTransferDTO(SqlRowSet row){
        TransferDTO transfer = new TransferDTO();
        transfer.setTransferId(row.getInt("transfer_id"));
        transfer.setSenderName(row.getString("sender_name"));
        transfer.setReceiverName(row.getString("receiver_name"));
        transfer.setTeBucks(row.getDouble("te_bucks"));
        return transfer;
    }

}
