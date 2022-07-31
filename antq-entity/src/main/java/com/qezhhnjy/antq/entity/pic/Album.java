package com.qezhhnjy.antq.entity.pic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zhaoyangfu
 * @since 2022/7/27-22:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("pic_album")
public class Album extends BaseEntity {

    private Long    id;
    private String  title;
    private String  station;
    // 封面
    private String  cover;
    private Integer count;
    private String  tag;
    private String  author;
    private String  model;
    private String  summary;

    @TableField(exist = false)
    private List<PicInfo> infoList;
}
