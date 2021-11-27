package com.qezhhnjy.antq.web.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 获取登录用户信息
 * Created by macro on 2020/6/17.
 */
@Component
@Slf4j
public class LoginUserHolder {

    /**
     * {"user_name":"macro","scope":["all"],"id":1,"exp":1638001321,"authorities":["ADMIN"],"jti":"57f47f2b-cc40-4e5a-bc6f-20ffdc58a9d6","client_id":"client-app"}
     */
    public String getCurrentUser() {
        //从Header中获取用户信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String userStr = request.getHeader("user");
        log.info("userStr=>{}", userStr);
        return userStr;
/*
        JSONObject userJsonObject = new JSONObject(userStr);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userJsonObject.getStr("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return userDTO;
*/
    }
}
