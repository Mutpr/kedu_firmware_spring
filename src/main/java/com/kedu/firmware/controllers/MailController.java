package com.kedu.firmware.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.firmware.DTO.MailCarbonCopyDTO;
import com.kedu.firmware.DTO.MailDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.MailCarbonCopyService;
import com.kedu.firmware.services.MailService;
import com.kedu.firmware.services.UsersService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailServ;
	
	@Autowired
	private UsersService usersServ;
	
	@Autowired
	private MailCarbonCopyService mailCarbonCopyServ;
	
	@Autowired
    private HttpSession session;
	
	//-----------------------------
		//메일 작성
		//메일 작성에 대해
		//메일 작성을 하면 메일함이 생성된다
		//해당 메일함의 seq를 가진 메일이 작성된다.
		//회신할때는 메일함이 생성되는 것이 아닌, 해당 메일함의 seq를 가진 새로운 메일이 추가된다.
		//즉, createMail은 새로운 메일함과 메일을 동시에 생성하는 것

		
		@PostMapping
		@Transactional
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
				UsersDTO user = usersServ.selectUserByEmail(to);
				
				if(user == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				}
				
				
	            System.out.println("Subject: " + subject);
	            System.out.println("Message: " + message);
	            
	            // 세션에 저장된 유저코드(로그인 아이디)로 유저 seq찾아서 저장
	            String user_code = (String) session.getAttribute("loginID");
	            UsersDTO usersdto = usersServ.getMemberById(user_code);
	            int loginID = usersdto.getUsers_seq();
	            
	            if (replyToMailId != null) {
	            	//회신 메일 처리
	            	System.out.println("회신하고자하는 메일의 ID " + replyToMailId);
	            	// 원본 메일의 MAILBOX_SEQ 가져오기위해 서비스로 replyToMailID넘겨준다.
	                
	            	MailDTO maildto = new MailDTO(0, loginID, replyToMailId, subject, message, null, null, null, 'N', 'N', 'Y');
	            	mailServ.replyMail(maildto);
	            	// 이 시점에서 새로 보낸 메일의 seq가 반환되어 mail_carbon_copy_seq에 들어간다
	            	int mail_seq = maildto.getMail_seq();
	            	System.out.println("carbon에들어갈 seq" + mail_seq);
	            	System.out.println(to);
	            	mailCarbonCopyServ.saveMailRecipient(new MailCarbonCopyDTO(0,to,mail_seq,0,0,"reply","Y"));
	            	
	            }else {
	            	//새로운 메일 작성 처리
	            	MailDTO maildto = new MailDTO(0,loginID,0,subject,message,null,null,null,'N','N','Y');
	            	mailServ.insertMail(maildto);
	            	// 이 시점에서 새로 보낸 메일의 seq가 반환되어 mail_carbon_copy_seq에 들어간다
	            	int mail_seq = maildto.getMail_seq();
	            	System.out.println("carbon에들어갈 seq" + mail_seq);
	            	System.out.println(to);
	            	mailCarbonCopyServ.saveMailRecipient(new MailCarbonCopyDTO(0,to,mail_seq,0,0,"send","Y"));
	            	
	            }
	            
	            return ResponseEntity.ok("메일이 성공적으로 전송되었습니다.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body("메일 전송 중 오류가 발생했습니다.");
	        }
			
			
		}
			
		@GetMapping
		public ResponseEntity<Map<String, Object>> get(
		        @RequestParam(value = "seq", required = false) Integer seq,
		        @RequestParam(value = "query", required = false) String query,
		        @RequestParam(value = "page", defaultValue = "1") int page, // 추가된 부분: 페이지 번호
		        @RequestParam(value = "size", defaultValue = "10") int size // 추가된 부분: 페이지 당 메일 수
		) { // 모호성 문제(ambious)로 매핑을 나누지않고 같은 매핑안에서 사용해야한다. 

		    // 분기점을 내부에서 만든다.
		    // 사용자가 메일함 목록 선택했을때, 해당 메일함의 메일들이 출력
		    if(seq != null) {
		        System.out.println("seq 반환");
		        return ResponseEntity.ok(Map.of("mails", mailServ.selectByMailSeq(seq), "total", 1));
		    } 

		    // 메일 리스트반환
		    // 메일함 목록을 보여주어야하기때문에 각 메일함에서 첫번째로 작성된 메일들만 출력해야한다.
		    List<MailDTO> list = mailServ.getAllMails((String)session.getAttribute("loginID"));

		    // 검색어가 있는 경우 필터링
		    if (query != null && !query.isEmpty()) {
		    	System.out.println("검색어는 " + query);
		        list = list.stream()
		            .filter(mail -> mail.getMail_title().contains(query) || mail.getMail_content().contains(query))
		            .collect(Collectors.toList());
		        
		    }
		    
//		    // 검색기능 테스트 코드
//		    System.out.println(list.size()); 
//		 // 필터링된 list의 각 요소 출력
//		    list.forEach(mail -> {
//		        System.out.println("Mail Seq: " + mail.getMail_seq());
//		        System.out.println("Mail Title: " + mail.getMail_title());
//		        System.out.println("Mail Content: " + mail.getMail_content());
//		        System.out.println("Mail Sender User Seq: " + mail.getMail_sender_user_seq());
//		        System.out.println("Mailbox Seq: " + mail.getMailbox_seq());
//		        System.out.println("Mail Received Date: " + mail.getMail_received_date());
//		        System.out.println("------------------------------");
//		        
//		    });

		    // 전체 메일 수 계산
		    int totalMails = list.size();

		    // 페이징 처리
		    int start = (page - 1) * size;
		    int end = Math.min(start + size, list.size());
		    List<MailDTO> paginatedList = list.subList(start, end);

		    // 반환할 데이터 맵 구성
		    Map<String, Object> response = new HashMap<>();
		    response.put("mails", paginatedList);
		    response.put("total", totalMails);

		    return ResponseEntity.ok(response);
		}
		
		//선택된 메일 삭제
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable int id){
			int result = mailServ.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		
		//회신일 경우에 받는사람 입력란에 이메일 자동으로 완성해주기위한 기능
	    @GetMapping("/{replyToMailId}/replyemail")
	    public ResponseEntity<String> getReplyEmail(@PathVariable Integer replyToMailId) {
	        try {
	            List<MailDTO> originalMail = mailServ.selectByMailSeq(replyToMailId);
	            
	            UsersDTO userdto =  usersServ.findUserBySeq(originalMail.get(0).getMail_sender_user_seq());
	            
	            
	            System.out.println("회신할 사람의 이메일은" + userdto.getUsers_email());
	            if (userdto.getUsers_email() == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	            
	            return ResponseEntity.ok(userdto.getUsers_email());
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
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
