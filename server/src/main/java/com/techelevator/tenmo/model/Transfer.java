package com.techelevator.tenmo.model;


import java.time.LocalDateTime;

public class Transfer {
    private int transferId;
    private int senderId;
    private int receiverId;
    private double teBucks;
    private String status;
    private LocalDateTime dateCreated;



    public Transfer(){

    }

    public Transfer(int transferId, int senderId, int receiverId, double teBucks, String status, LocalDateTime dateCreated){
        this.transferId = transferId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.teBucks = teBucks;
        this.status = status;
        this.dateCreated = dateCreated;
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
