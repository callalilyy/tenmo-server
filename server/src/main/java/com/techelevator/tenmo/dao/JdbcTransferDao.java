package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
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
    public List<Transfer> findAll(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, sender_id, receiver_id, te_bucks, status, date_created,sender_name, receiver_name" +
                "FROM transfer WHERE sender_id = ? OR receiver_id = ? ORDER BY date_created;";
        //NOT SURE IF USERID NEEDS TO PASSED TWICE
        //NOT SURE IF I NEED TO LIST ALL COLUMNS IN SELECT STATEMENT TO MAPROWTOTRANSFER CORRECTLY or if I can select only columns I want
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while(result.next()){
            transfers.add(mapRowToTransfer(result));
        }
        return transfers;
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
        Transfer transfer = new Transfer();
        transfer.setTransferId(row.getInt("transfer_id"));
        transfer.setSenderId(row.getInt("sender_id"));
        transfer.setReceiverId(row.getInt("receiver_id"));
        transfer.setTeBucks(row.getDouble("te_bucks"));
        transfer.setStatus(row.getString("status"));
        transfer.setDateCreated(row.getTimestamp("date_created").toLocalDateTime());
        transfer.setSenderName(row.getString("sender_name"));
        transfer.setReceiverName(row.getString("receiver_name"));

        return transfer;
    }

}
