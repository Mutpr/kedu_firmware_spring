package com.kedu.firmware.controllers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.kedu.firmware.DTO.MailAttachmentDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.MailAttachmentService;

@RestController
@RequestMapping("mailattachment")
public class MailAttachmentController {

	@Autowired
	private MailAttachmentService mailAttachmentServ;
	
	// 첨부 파일 다운로드 처리
	@GetMapping("/{attachmentId}")
	public ResponseEntity<Resource> downloadAttachment(@PathVariable int attachmentId) {
	    try {
	        MailAttachmentDTO attachment = mailAttachmentServ.getAttachmentById(attachmentId);
	        Path filePath = Paths.get("C:/upload", attachment.getStored_file_name()); // 파일 저장 경로
	        Resource resource = new UrlResource(filePath.toUri());

	        // 파일 이름을 UTF-8로 인코딩
	        String encodedFileName = UriUtils.encode(attachment.getOriginal_file_name(), StandardCharsets.UTF_8);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
	                .body(resource);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	    
	    
	    // 첨부 파일 삭제 처리
	    @DeleteMapping("/{attachmentId}")
	    public ResponseEntity<String> deleteAttachment(@PathVariable int attachmentId) {
	        try {
//	            MailAttachmentDTO attachment = mailAttachmentServ.getAttachmentById(attachmentId);
//	            String loginID = (String) session.getAttribute("loginID");
//	            UsersDTO currentUser = usersServ.getMemberById(loginID);
//
//	            // 첨부 파일 업로더와 현재 로그인한 사용자가 동일한지 확인
//	            if (attachment.getUploader_seq() == currentUser.getUsers_seq()) {
	                mailAttachmentServ.deleteAttachment(attachmentId); // 첨부 파일 삭제 서비스 호출
	                return ResponseEntity.ok("첨부 파일이 성공적으로 삭제되었습니다.");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("첨부 파일을 삭제할 권한이 없습니다.");
//	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("첨부 파일 삭제 중 오류가 발생했습니다.");
	        }
	    }
	    

	    
	
}
