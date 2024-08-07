package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.PrivateMessageDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrivateMessageDAO {

    @Autowired
    private SqlSession mybatis;

    public void saveMessage(PrivateMessageDTO message) {
        mybatis.insert("privateMessage.insert", message);
    }

    public List<PrivateMessageDTO> selectAll() {
        return mybatis.selectList("privateMessage.selectAll");
    }

    public PrivateMessageDTO selectById(int id) {
        return mybatis.selectOne("privateMessage.selectById", id);
    }

    public List<PrivateMessageDTO> selectByParticipants(String sender, String receiver) {
        Map<String, Object> params = new HashMap<>();
        params.put("sender", sender);
        params.put("receiver", receiver);
        return mybatis.selectList("privateMessage.selectByParticipants", params);
    }
}
