package com.qezhhnjy.antq.common.vo.sys;

import com.qezhhnjy.antq.entity.sys.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhaoyangfu
 * @date 2021/12/21-15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NoticeVO {

    private Notice notice;
}
