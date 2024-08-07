package com.kedu.firmware.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.MailBoxDTO;

@Repository
public class MailBoxDAO {

	@Autowired
	private SqlSession mybatis;
	
	//메일함 생성 (메일 작성될때)
	public void insertMailBox(MailBoxDTO dto) {
		mybatis.insert("MailBox.insertMailBox", dto);
	}
	
	public int deleteById(int id) {
		return mybatis.delete("MailBox.deleteSelectedMailBox", id);
	}
	
}
