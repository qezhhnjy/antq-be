package com.qezhhnjy.antq.web.feign;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyangfu
 * @date 2022/2/17-14:40
 */
@Service
public class OauthServiceFallback implements OauthService {

    @Override
    public BaseResult<MemoryInfo> info() {
        return BaseResult.success();
    }
}
