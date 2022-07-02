package com.qezhhnjy.antq.entity.out;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhaoyangfu
 * @since 2022/7/2-14:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("out_hiking_trail")
public class HikingTrail extends BaseEntity {

    private Long    id;
    private String  avatar;
    private String  title;
    private String  summary;
    private Integer difficulty;
    private Integer recommend;
    private String  icon;
    private String  tags;
    private String  iframe;
    private String  detail;
    private Integer distance;
    private Integer elevationRise;
    private Integer elevationFall;
    private Integer elevation;

    public static void handler(HikingTrail trail) {
        String iframe = trail.getIframe();
        trail.iframe = iframe.replace("width=\"560\" height=\"400\"", "width=\"100%\" height=\"800\"");
    }
}
