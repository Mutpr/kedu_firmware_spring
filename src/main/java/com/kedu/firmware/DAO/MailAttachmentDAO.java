package com.kedu.firmware.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.MailAttachmentDTO;

@Repository
public class MailAttachmentDAO {

	@Autowired
	private SqlSession mybatis;
	
	// 첨부 파일 저장
    public void insertAttachment(MailAttachmentDTO attachment) {
    	mybatis.insert("mailattachment.insertAttachment", attachment);
    }

    // 첨부 파일 정보 가져오기
    public MailAttachmentDTO selectAttachmentById(int attachmentId) {
        return mybatis.selectOne("mailattachment.selectAttachmentById", attachmentId);
    }

    // 메일에 포함된 모든 첨부 파일 목록 가져오기
    public List<MailAttachmentDTO> selectAttachmentsByMailSeq(int mailSeq) {
        return mybatis.selectList("mailattachment.selectAttachmentsByMailSeq", mailSeq);
    }

    // 첨부 파일 삭제
    public void deleteAttachment(int attachmentId) {
    	mybatis.delete("mailattachment.deleteAttachment", attachmentId);
    }
	
}
