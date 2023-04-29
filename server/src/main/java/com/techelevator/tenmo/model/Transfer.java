package com.techelevator.tenmo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Transfer {
    @JsonProperty("transfer_id")
    private int transferId;
    @JsonProperty("sender_id")
    private int senderId;
    @JsonProperty("receiver_id")
    private int receiverId;
    @JsonProperty("te_bucks")
    private double teBucks;
    private String status;
    @JsonProperty("date_created")
    private LocalDateTime dateCreated;
    @JsonProperty("sender_name")
    private String senderName;
    @JsonProperty("receiver_name")
    private String receiverName;


    public Transfer(){

    }

    public Transfer(int transferId, int senderId, int receiverId, double teBucks, String status, LocalDateTime dateCreated, String senderName, String receiverName){
        this.transferId = transferId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.teBucks = teBucks;
        this.status = status;
        this.dateCreated = dateCreated;
        this.senderName = senderName;
        this.receiverName = receiverName;
    }


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getTeBucks() {
        return teBucks;
    }

    public void setTeBucks(double teBucks) {
        this.teBucks = teBucks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", teBucks=" + teBucks +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
