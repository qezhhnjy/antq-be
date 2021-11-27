package com.qezhhnjy.antq.oauth.service;

import cn.hutool.core.collection.CollUtil;
import com.qezhhnjy.antq.common.enums.ResultCode;
import com.qezhhnjy.antq.oauth.component.SecurityUser;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
public class SecurityUserService implements UserDetailsService {

    private static final List<SecurityUser> userList = new ArrayList<>();

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");

        SecurityUser macro = new SecurityUser();
        macro.setId(1L);
        macro.setUsername("macro");
        macro.setPassword(password);
        macro.setEnabled(true);
        macro.setAuthorities(CollUtil.toList(new SimpleGrantedAuthority("ADMIN")));

        SecurityUser andy = new SecurityUser();
        andy.setId(2L);
        andy.setUsername("andy");
        andy.setPassword(password);
        andy.setEnabled(false);
        andy.setAuthorities(CollUtil.toList(new SimpleGrantedAuthority("TEST")));

        userList.add(macro);
        userList.add(andy);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = userList.stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(ResultCode.USERNAME_PASSWORD_ERROR.msg));
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
