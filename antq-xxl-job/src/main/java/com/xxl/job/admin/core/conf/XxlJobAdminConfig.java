package com.xxl.job.admin.core.conf;

import com.xxl.job.admin.core.alarm.JobAlarmCaller;
import com.xxl.job.admin.core.scheduler.XxlJobScheduler;
import com.xxl.job.admin.dao.*;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */

@Configuration
@ConfigurationProperties(prefix = "xxl.job")
@Data
public class XxlJobAdminConfig implements InitializingBean, DisposableBean {

    private static XxlJobAdminConfig adminConfig = null;

    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }


    // ---------------------- XxlJobScheduler ----------------------

    private XxlJobScheduler xxlJobScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        xxlJobScheduler = new XxlJobScheduler();
        xxlJobScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        xxlJobScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    private String i18n;

    private String accessToken;

    private String emailFrom;

    private int triggerPoolFastMax;

    private int triggerPoolSlowMax;

    private int logRetentionDays;

    // dao, service

    @Resource
    private XxlJobLogDao       xxlJobLogDao;
    @Resource
    private XxlJobInfoDao      xxlJobInfoDao;
    @Resource
    private XxlJobRegistryDao  xxlJobRegistryDao;
    @Resource
    private XxlJobGroupDao     xxlJobGroupDao;
    @Resource
    private XxlJobLogReportDao xxlJobLogReportDao;
    @Resource
    private JavaMailSender     mailSender;
    @Resource
    private DataSource     dataSource;
    @Resource
    private JobAlarmCaller jobAlarmCaller;


    public String getI18n() {
        if (!Arrays.asList("zh_CN", "zh_TC", "en").contains(i18n)) {
            return "zh_CN";
        }
        return i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public int getTriggerPoolFastMax() {
        return Math.max(triggerPoolFastMax, 200);
    }

    public int getTriggerPoolSlowMax() {
        return Math.max(triggerPoolSlowMax, 100);
    }

    public int getLogRetentionDays() {
        if (logRetentionDays < 7) {
            return -1;  // Limit greater than or equal to 7, otherwise close
        }
        return logRetentionDays;
    }

    public XxlJobLogDao getXxlJobLogDao() {
        return xxlJobLogDao;
    }

    public XxlJobInfoDao getXxlJobInfoDao() {
        return xxlJobInfoDao;
    }

    public XxlJobRegistryDao getXxlJobRegistryDao() {
        return xxlJobRegistryDao;
    }

    public XxlJobGroupDao getXxlJobGroupDao() {
        return xxlJobGroupDao;
    }

    public XxlJobLogReportDao getXxlJobLogReportDao() {
        return xxlJobLogReportDao;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JobAlarmCaller getJobAlarmCaller() {
        return jobAlarmCaller;
    }

}
