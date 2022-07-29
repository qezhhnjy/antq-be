package com.qezhhnjy.antq.entity.pic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhaoyangfu
 * @since 2022/7/27-22:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pic_info")
@Accessors(chain = true)
public class PicInfo extends BaseEntity {

    private Long id;
    private Long albumId;
    private String url;
    private Integer sequence;
}
