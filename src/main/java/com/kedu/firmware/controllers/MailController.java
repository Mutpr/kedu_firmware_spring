package com.kedu.firmware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.firmware.DTO.MailDTO;
import com.kedu.firmware.services.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailServ;
	
	//-----------------------------
		//메일 작성
		//메일 작성에 대해
		//메일 작성을 하면 메일함이 생성된다
		//해당 메일함의 seq를 가진 메일이 작성된다.
		//회신할때는 메일함이 생성되는 것이 아닌, 해당 메일함의 seq를 가진 새로운 메일이 추가된다.
		//즉, createMail은 새로운 메일함과 메일을 동시에 생성하는 것
	
		@PostMapping
		public ResponseEntity<String> createMail(
				@RequestParam("to") String to,
	            @RequestParam("subject") String subject,
	            @RequestParam("message") String message,
	            @RequestParam(value = "attachments", required = false) MultipartFile[] attachments,
	            @RequestParam(value = "replyToMailId", required = false) Integer replyToMailId) {
			System.out.println("첨부파일 있는지 확인 중");
			try {
	            // 첨부 파일 처리
				if (attachments != null) {
		            for (MultipartFile file : attachments) {
		                if (!file.isEmpty()) { //첨부된 파일이 있다면 파일을 저장
		                    String fileName = file.getOriginalFilename();
		                    // 파일 저장 시점 fileServ를 불러와야 할 것이다.
		                    System.out.println("Received file: " + fileName);
		                }
		            }
				}
	            // 메일 데이터 전송 시점
	            // 보낸이 받고
	            // 보내는 이는 일단 임의의 값 집어넣어서 테스트
				System.out.println("To: " + to);
	            System.out.println("Subject: " + subject);
	            System.out.println("Message: " + message);
	            
	            //!!!!받는 사람도 구현해야함!!!!!!
	            
	            
	            int loginID = 1;
	            
	            if (replyToMailId != null) {
	            	//회신 메일 처리
	            	System.out.println("회신하고자하는 메일의 ID " + replyToMailId);
	            	// 원본 메일의 MAILBOX_SEQ 가져오기위해 서비스로 replyToMailID넘겨준다.
	                
	            	mailServ.replyMail(new MailDTO(0, loginID, replyToMailId, subject, message, null, null, null, 'N', 'N', 'Y'));
	            }else {
	            	//새로운 메일 작성 처리
	            	mailServ.insertMail(new MailDTO(0,loginID,0,subject,message,null,null,null,'N','N','Y'));
	            }
	            
	            return ResponseEntity.ok("메일이 성공적으로 전송되었습니다.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("메일 전송 중 오류가 발생했습니다.");
	        }
			
			
		}
			
		//메일 출력
		@GetMapping
		public ResponseEntity<List<MailDTO>> get(Integer seq){ //모호성 문제(ambious)로 매핑을 나누지않고 같은 매핑안에서 사용해야한다. 
			
			//분기점을 내부에서 만든다.
			//사용자가 메일함 목록 선택했을때, 해당 메일함의 메일들이 출력
			if(seq != null) {
				System.out.println("seq 반환");
				return ResponseEntity.ok(mailServ.selectByMailSeq(seq));
				// 메일 제목으로 찾는 것이 아니라 메일seq로 찾는 것으로 수정이 필요해보인다.
				// 메일 테이블에 수신종류 칼럼을 추가해서 회신이나 전달 메일이라는 정보가 있을때로 if문을 제어해야할 것이다. 
			} 
			
			//메일 리스트반환
			//메일함 목록을 보여주어야하기때문에 각 메일함에서 첫번째로 작성된 메일들만 출력해야한다.
			List<MailDTO> list = mailServ.getAllMails();

			return ResponseEntity.ok(list);
		}
		
		//선택된 메일 삭제
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable int id){
			int result = mailServ.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
//		//보내는 이의 seq 값으로 보내는이의 정보 받아오기 (이름과 이메일 등)
//		@GetMapping("/{sender_user_seq}")
//		public ResponseEntity<UsersDTO> get(int sender_user_seq){  
//			
//			//메일 리스트반환
//			UsersDTO senderInfo = usersServ.getSenderInfo(sender_user_seq);
//
//			return ResponseEntity.ok(senderInfo);
//		}		

		
}