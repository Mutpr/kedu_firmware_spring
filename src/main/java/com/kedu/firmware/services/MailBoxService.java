package com.kedu.firmware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.firmware.DAO.MailBoxDAO;
import com.kedu.firmware.DAO.MailDAO;

@Service
public class MailBoxService {

	@Autowired
	private MailBoxDAO mailboxdao;
	
	@Autowired
	private MailDAO maildao;
	
	public int deleteById(int id) {
		
//		일단 메일함 안의 메일들을 전체 삭제하고 메일함까지 삭제해준다.
		maildao.deleteMailsById(id);
		return mailboxdao.deleteById(id);
	}
}
