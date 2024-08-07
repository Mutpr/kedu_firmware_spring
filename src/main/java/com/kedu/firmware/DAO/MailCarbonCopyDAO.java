package com.kedu.firmware.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.MailCarbonCopyDTO;



@Repository
public class MailCarbonCopyDAO {

	@Autowired
	private SqlSession mybatis;
	
	//메일 작성
	public void saveMailRecipient(MailCarbonCopyDTO mailcarboncopydto) {
		mybatis.insert("MailCarbonCopy.saveMailRecipient", mailcarboncopydto);
	}
	
}
