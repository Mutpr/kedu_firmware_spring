package com.kedu.firmware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.firmware.DAO.MailCarbonCopyDAO;
import com.kedu.firmware.DTO.MailCarbonCopyDTO;


@Service
public class MailCarbonCopyService {
	
	@Autowired
	private MailCarbonCopyDAO mailcarboncopydao; 
	
	public void saveMailRecipient(MailCarbonCopyDTO mailCarbonCopydto) {
		mailcarboncopydao.saveMailRecipient(mailCarbonCopydto);
	}
	
	
	
}
