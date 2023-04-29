package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> findAll(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, sender_name, receiver_name, te_bucks, date_created" +
                "FROM transfer WHERE sender_id = ? OR receiver_id = ? ORDER BY date_created;";
        //NOT SURE IF USERID NEEDS TO PASSED TWICE
        //NOT SURE IF I NEED TO LIST ALL COLUMNS IN SELECT STATEMENT TO MAPROWTOTRANSFER CORRECTLY
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while(result.next()){
            transfers.add(mapRowToTransfer(result));
        }
        return transfers;
    }

    @Override
    public Transfer findByTransferId(String transferId) {
        return null;
    }

   


    @Override
    public Transfer create(Transfer transfer) {
        return transfer;
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
