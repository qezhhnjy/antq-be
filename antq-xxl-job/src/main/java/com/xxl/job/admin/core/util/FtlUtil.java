package com.xxl.job.admin.core.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import lombok.extern.slf4j.Slf4j;

/**
 * ftl util
 *
 * @author xuxueli 2018-01-17 20:37:48
 */
@Slf4j
public class FtlUtil {

    private static final BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build();     //BeansWrapper.getDefaultInstance();

    public static TemplateHashModel generateStaticModel(String packageName) {
        try {
            TemplateHashModel staticModels = wrapper.getStaticModels();
            return (TemplateHashModel) staticModels.get(packageName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
