package com.kedu.firmware.services;


import com.kedu.firmware.DAO.MailBoxDAO;
import com.kedu.firmware.DAO.MailDAO;
import com.kedu.firmware.DAO.UsersDAO;
import com.kedu.firmware.DTO.MailBoxDTO;
import com.kedu.firmware.DTO.MailDTO;
import com.kedu.firmware.DTO.UsersDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailService {


	@Autowired
	private MailDAO maildao;
	
	@Autowired
	private MailBoxDAO mailboxdao;
	
	@Autowired
	private UsersDAO usersdao;
	
	// 메일작성
	@Transactional //원자성을 위해서 둘 중 하나라도 실패하면 둘 다 취소되어야한다.
	public void insertMail(MailDTO maildto) {
//		mailboxdao.insertMailBox();
//		maildao.insertMail(dto);
		
		// 메일박스 생성
        MailBoxDTO mailboxDTO = new MailBoxDTO();
        mailboxDTO.setUser_seq(maildto.getMail_sender_user_seq()); //컨트롤러쪽에서 작업필요
        mailboxdao.insertMailBox(mailboxDTO);

        // 생성된 메일박스의 시퀀스 값
        int generatedMailboxSeq = mailboxDTO.getMailbox_seq();

        System.out.println(generatedMailboxSeq);
        // MailDTO에 메일박스 시퀀스 값 설정
        maildto.setMailbox_seq(generatedMailboxSeq);

        // 메일 삽입
        maildao.insertMail(maildto);
		
	}
	
	//회신 작성 서비스이다. insertMail서비스는 새로운 mailbox_seq를 생성함과 동시에 기억하지만
	//회신 작성 서비스는 클라이언트측에서 선택한 메일함의 seq를 가져온다.
	public void replyMail(MailDTO maildto) {
		// 원본 메일의 MAILBOX_SEQ를 가져와 설정
        MailDTO originalMail = maildao.getMailById(maildto.getMailbox_seq());
        maildto.setMailbox_seq(originalMail.getMailbox_seq());
		maildao.replyMail(maildto);
	}
		
	public List<MailDTO> getAllMails(String loginID){
		
		
		UsersDTO usersdto =  usersdao.findUserByCode(loginID);
		String Email = usersdto.getUsers_email();
		return maildao.selectAllMails(Email);
	}
	
	public List<MailDTO> selectByMailSeq(int seq){
		return maildao.selectByMailSeq(seq);
	}
	
	public int deleteById(int id) {
		return maildao.deleteById(id);
	}
	
	
//	public List<MailDTO> findEmailByMailSeq(int seq){
//		return maildao.selectBy
//	}
	
//	//유저 정보 받아오기
//	public UsersDTO getUserInfo(int sender_user_seq) {
//		return userdao.getUserInfo();
//	}
	
	
}
