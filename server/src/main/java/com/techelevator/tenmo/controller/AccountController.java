package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
//@PreAuthorize("isAuthenticated()")
public class AccountController {
    @Autowired
    JdbcAccountDao accountDao;
    @Autowired
    JdbcTransferDao transferDao;
    @Autowired
    JdbcUserDao jdbcUserDao;






    //get account balance IS WORKING!!!!!!!
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public double myBalance(Principal principal){
        int id = jdbcUserDao.findIdByUsername(principal.getName());
        System.out.println(id);
        return accountDao.getBal(id);
    }


    //get list of users
    @RequestMapping(path = "/get-users", method = RequestMethod.GET)
    public List<String> userList(Principal principal){
        return jdbcUserDao.findAll(principal.getName());
    }




    //get transfers sent or received
    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<TransferDTO> myTransfers(Principal principal){
        return transferDao.getTransfers(principal.getName());
    }



    @RequestMapping(path="/transfer/{id}", method = RequestMethod.GET)
    public TransferDTO getOneTransfer(@PathVariable int transferId, Principal principal){
        return transferDao.getTransfer(transferId);
    }



    @RequestMapping(path="/transfer", method = RequestMethod.POST)
    public TransferDTO sendTransfer(Principal principal, @RequestBody Transfer transfer){
       return transferDao.create(principal.getName(), transfer.getTeBucks() , transfer.getReceiverName());

    }



}
