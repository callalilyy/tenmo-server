package com.techelevator.tenmo.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcAccountDao accountDao;
    @Autowired
    private JdbcUserDao userDao;


    @Override
    public List<TransferDTO> getTransfers(String username) {
        List<TransferDTO> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, sender_name, receiver_name, te_bucks, date_created, status FROM transfer\n" +
                "WHERE sender_name = ? OR receiver_name = ? ORDER BY date_created;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username, username);
        while(result.next()){
            transfers.add(mapRowToTransferDTO(result));
        }
        return transfers;
    }


    @Override
    public TransferDTO getTransfer(int transferId) {
        String sql = "SELECT transfer_id, sender_name, receiver_name, te_bucks, date_created, status" +
        "FROM transfer WHERE transfer_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        if(result.next()){
            return mapRowToTransferDTO(result);
        }
        return null;
    }




    @Override
    public TransferDTO create( String senderName, Double transferAmount,String receiverName) {

//        String sql = "SELECT user_id FROM user WHERE username = ?;";
//        Integer receiverId = jdbcTemplate.queryForObject(sql, Integer.class, receiverName);


//        String sol = "SELECT user_id FROM user WHERE username = ?;";
//        Integer senderId = jdbcTemplate.queryForObject(sql, Integer.class, senderName);


        Integer senderId = userDao.findIdByUsername(senderName);
        accountDao.debit(senderId,transferAmount);

        //select user
        Integer receiverId = userDao.findIdByUsername(receiverName);
        accountDao.credit(receiverId,transferAmount);

        String sql = "INSERT INTO transfer (sender_id, receiver_id, te_bucks, status, date_created,sender_name, receiver_name)" +
                "VALUES(?,?,?,?,?,?,?) RETURNING transfer_id;";
        Integer newId;
        String status = "approved";
        try {
            newId = jdbcTemplate.queryForObject(sql, Integer.class, senderId, receiverId, transferAmount,status, LocalDateTime.now(), senderName, receiverName);
            if(newId != null){
                return getTransfer(newId);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Could not create transfer");
        }
        return null;
    }


//    private Transfer mapRowToTransfer(SqlRowSet row){
//        Transfer detailedTransfer = new Transfer();
//        detailedTransfer.setTransferId(row.getInt("transfer_id"));
//        detailedTransfer.setSenderId(row.getInt("sender_id"));
//        detailedTransfer.setReceiverId(row.getInt("receiver_id"));
//        detailedTransfer.setTeBucks(row.getDouble("te_bucks"));
//        detailedTransfer.setStatus(row.getString("status"));
//        detailedTransfer.setDateCreated(row.getTimestamp("date_created").toLocalDateTime());
//        detailedTransfer.setSenderName(row.getString("sender_name"));
//        detailedTransfer.setReceiverName(row.getString("receiver_name"));
//
//        return detailedTransfer;
//    }


    private TransferDTO mapRowToTransferDTO(SqlRowSet row){
        TransferDTO transfer = new TransferDTO();
        transfer.setTransferId(row.getInt("transfer_id"));
        transfer.setSenderName(row.getString("sender_name"));
        transfer.setReceiverName(row.getString("receiver_name"));
        transfer.setTeBucks(row.getDouble("te_bucks"));
        transfer.setDateCreated(row.getTimestamp("date_created").toLocalDateTime());
        transfer.setStatus(row.getString("status"));
        return transfer;
    }

}
