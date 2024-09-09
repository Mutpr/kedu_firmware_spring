package com.kedu.firmware.DAO;


import com.kedu.firmware.DTO.AuthDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthDAO {

    @Autowired
    private SqlSession mybatis;

    public Boolean vailDate(AuthDTO authDTO){
        Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", authDTO.getUsersSeq());
        params.put("loginID", authDTO.getLoginID());
        params.put("usersName", authDTO.getUsersName());
        params.put("employeeId", authDTO.getEmployeeId());
        params.put("rank", authDTO.getRank());
        params.put("isAdmin", authDTO.getIsAdmin());

        return mybatis.selectOne("auth.getAccount",params);
    }

}
