package com.kedu.firmware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.firmware.services.MailBoxService;

@RestController
@RequestMapping("/mailbox")
public class MailBoxController {

	@Autowired
	private MailBoxService mailBoxServ;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMailbox(@PathVariable int id){
	    int result = mailBoxServ.deleteById(id);
	    return ResponseEntity.ok().build();
	}
}
