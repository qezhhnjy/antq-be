package com.qezhhnjy.antq.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.qezhhnjy.antq.common.consts.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 * Created by macro on 2020/6/17.
 */
@Component
@Slf4j
@Order(0)
public class AuthGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("exchange.getRequest()=>{}", exchange.getRequest());
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            log.debug("AuthGlobalFilter.filter() user:{}", userStr);
            Long userId = JSONUtil.parseObj(userStr).getLong("id");
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.HEADER_USER_ID, String.valueOf(userId)).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return chain.filter(exchange);
    }

}
