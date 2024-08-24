package com.kedu.firmware.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.firmware.DAO.MailAttachmentDAO;
import com.kedu.firmware.DTO.MailAttachmentDTO;

@Service
public class MailAttachmentService {

    @Autowired
    private MailAttachmentDAO attachmentDAO;

    // 첨부 파일 저장
    @Transactional
    public void saveAttachment(int mailSeq, MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        
        Path filePath = Paths.get("C:\\upload", storedFileName);

        // 파일을 저장할 디렉터리가 존재하지 않으면 생성
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        
        // 파일 저장
        Files.copy(file.getInputStream(), filePath);

        // DB 저장
        MailAttachmentDTO  attachment = new MailAttachmentDTO(0, mailSeq, originalFileName, storedFileName, file.getSize(), file.getContentType(), null);
        attachmentDAO.insertAttachment(attachment);
    }

    // 파일 다운로드
    public MailAttachmentDTO getAttachmentById(int attachmentId) {
        return attachmentDAO.selectAttachmentById(attachmentId);
    }
    
 // 특정 메일의 모든 첨부 파일 목록 가져오기
    public List<MailAttachmentDTO> getAttachmentsByMailSeq(int mailSeq) {
        return attachmentDAO.selectAttachmentsByMailSeq(mailSeq);
    }

    // 첨부 파일 삭제
    @Transactional
    public void deleteAttachment(int attachmentId) throws IOException {
        // 데이터베이스에서 첨부 파일 정보 가져오기
        MailAttachmentDTO attachment = attachmentDAO.selectAttachmentById(attachmentId);

        if (attachment != null) {
            // 서버에서 파일 삭제
            Path filePath = Paths.get("C:\\upload", attachment.getStored_file_name());
            Files.deleteIfExists(filePath);

            // 데이터베이스에서 첨부 파일 정보 삭제
            attachmentDAO.deleteAttachment(attachmentId);
        }
    }
    
}

