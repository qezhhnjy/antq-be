package com.qezhhnjy.antq.oauth.service;

import com.qezhhnjy.antq.common.enums.ResultCode;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.entity.sys.User;
import com.qezhhnjy.antq.oauth.component.SecurityUser;
import com.qezhhnjy.antq.service.sys.UserService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 用户管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class SecurityUserService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO vo = userService.getByUserName(username);
        SecurityUser securityUser;
        if (vo == null) throw new UsernameNotFoundException(ResultCode.USERNAME_PASSWORD_ERROR.msg);

        User user = vo.getUser();
        securityUser = new SecurityUser();
        securityUser.setId(user.getId());
        securityUser.setUsername(user.getUsername());
        securityUser.setPassword(user.getPassword());
        securityUser.setEnabled(user.getStatus() == 0);
        securityUser.setAuthorities(vo.getRoleList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList()));

        if (!securityUser.isEnabled()) {
            throw new DisabledException(ResultCode.ACCOUNT_DISABLED.msg);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(ResultCode.ACCOUNT_LOCKED.msg);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(ResultCode.ACCOUNT_EXPIRED.msg);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(ResultCode.CREDENTIALS_EXPIRED.msg);
        }
        return securityUser;
    }

}
