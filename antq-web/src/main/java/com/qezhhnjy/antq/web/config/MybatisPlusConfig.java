package com.qezhhnjy.antq.web.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.qezhhnjy.antq.common.consts.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu - 2021/2/2 17:16
 */
@Configuration
@Slf4j
@EnableTransactionManagement
public class MybatisPlusConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "companyId", String.class, Const.COMPANY_ID);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

}
