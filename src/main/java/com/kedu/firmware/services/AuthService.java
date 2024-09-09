package com.kedu.firmware.services;

import com.kedu.firmware.DAO.AuthDAO;
import com.kedu.firmware.DTO.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthDAO authDAO;

    public boolean validation(AuthDTO authDTO) {
        boolean result = authDAO.vailDate(authDTO);
        System.out.println("result : " + result);
        return result;
    }
}
