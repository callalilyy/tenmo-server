package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferDTO {

    private int transferId;
    private String senderName;
    private String receiverName;
    private double teBucks;


    public TransferDTO(){};

    public TransferDTO(int transferId, String senderName, String receiverName, double teBucks) {
        this.transferId = transferId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.teBucks = teBucks;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
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

    public double getTeBucks() {
        return teBucks;
    }

    public void setTeBucks(double teBucks) {
        this.teBucks = teBucks;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "transferId=" + transferId +
                ", senderName=" + senderName +
                ", receiverName=" + receiverName +
                ", teBucks=" + teBucks +
                '}';
    }
}
