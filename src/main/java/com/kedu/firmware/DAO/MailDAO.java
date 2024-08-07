package com.kedu.firmware.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.MailDTO;

@Repository
public class MailDAO {

	@Autowired
	private SqlSession mybatis;
	
	//메일 작성
	public void insertMail(MailDTO dto) {
		mybatis.insert("Mail.insertMail", dto);
	}
	
	public void replyMail(MailDTO dto) {
		mybatis.insert("Mail.replyMail", dto);
	}
	
	// 원본 메일 가져오기 (회신 메일의 MAILBOX_SEQ 가져오기 위함)
    public MailDTO getMailById(int mailId) {
        return mybatis.selectOne("Mail.getMailById", mailId);
    }
	
	public List<MailDTO> selectAllMails(String Email){
		return mybatis.selectList("Mail.selectAll", Email);
	}
	
	public List<MailDTO> selectByMailSeq(int seq){ 
		return mybatis.selectList("Mail.selectBySeq", seq);
	}
	
	public int deleteById(int id) {
		return mybatis.delete("Mail.deleteSelectedMail", id);
	}
	
	public int deleteMailsById(int id) {
		return mybatis.delete("Mail.deleteSelectedMails", id);
	}
//	public UsersDTO getSenderInfo(int sender_user_seq) {
//		return mybatis.selectOne("User.selectSenderInfo", sender_user_seq);
//	}
	
}
