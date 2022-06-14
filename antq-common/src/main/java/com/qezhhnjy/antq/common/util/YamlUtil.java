package com.qezhhnjy.antq.common.util;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author zhaoyangfu
 * @since 2022/6/14-15:51
 */
public class YamlUtil {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        InputStream stream = ResourceUtil.getStream("jackson-test.yml");
        User user = yaml.loadAs(stream, User.class);
        System.out.println(user);
    }

    @Data
    public static class User {
        private String username;
        private String password;
    }
}
