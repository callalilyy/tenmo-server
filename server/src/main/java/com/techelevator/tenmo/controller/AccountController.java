package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {
    @Autowired
    JdbcAccountDao accountDao;

    //methods
    //get account balance
    //send a transfer
    //see transfers sent or received
    //retrieve details of transfer based on transfer id








}
