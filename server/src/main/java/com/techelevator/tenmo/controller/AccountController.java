package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
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

    //methods
    // example below
//    @RequestMapping(path="/notes", method = RequestMethod.POST)
//    public Note createNote(@RequestBody Note note){
//        return notesDao.createNote(note);
//    }




    //get account balance IS WORKING!!!!!!!
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public double getBalance(Principal principal){
        int id = jdbcUserDao.findIdByUsername(principal.getName());
        System.out.println(id);
        return accountDao.getBal(id);
    }


    //send a transfer
    //@RequestMapping(path)




    //get transfers sent or received
    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<TransferDTO> getTransfers(Principal principal){
        String username = principal.getName();
        return transferDao.getTransfers(username);
    }

    //retrieve full details of transfer based on transfer id
    //@RequestMapping

    @RequestMapping(path="/transfer/{id}", method = RequestMethod.GET)
    public TransferDTO getTransfer(Principal principal){
        int transferId =0;
        return transferDao.getTransfer(transferId);
    }


//    public List<Transfer> getAllTransfers() {
//        return transferDao.getAllTransfers();
//    }






}
