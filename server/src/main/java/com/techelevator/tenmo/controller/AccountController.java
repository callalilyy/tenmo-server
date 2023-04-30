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

@Component
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
    @RequestMapping(path = "/tenmo/{id}", method = RequestMethod.GET)
    public double getBalance(@PathVariable int id){
        //id = jdbcUserDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(id);

    }


    //send a transfer
    //@RequestMapping(path)


    //see transfers sent or received
    @RequestMapping(path="/tenmo", method = RequestMethod.GET)
    public List<TransferDTO> getTransfers(){
        //get current logged-in user's id
        //pass that userId as the id needed to locate the transfers
        //return the transfers
        return transferDao.getTransfers();
    }

    //retrieve full details of transfer based on transfer id
    //@RequestMapping


//    public List<Transfer> getAllTransfers() {
//        return transferDao.getAllTransfers();
//    }






}
