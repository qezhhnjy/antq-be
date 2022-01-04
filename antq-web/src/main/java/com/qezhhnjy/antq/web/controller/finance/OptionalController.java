package com.qezhhnjy.antq.web.controller.finance;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.entity.finance.FinanceOptional;
import com.qezhhnjy.antq.service.finance.OptionalService;
import com.qezhhnjy.antq.web.holder.LoginUserHolder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author zhaoyangfu
 * @date 2022/1/3-21:38
 */
@RestController
@RequestMapping("/finance-optional")
@Slf4j
public class OptionalController {

    @Resource
    private OptionalService optionalService;
    @Resource
    private LoginUserHolder loginUserHolder;

    @PostMapping("/add")
    @ApiOperation(value = "股票加关注")
    public BaseResult<FinanceOptional> add(@RequestBody @Valid FinanceOptional optional) {
        UserVO currentUser = loginUserHolder.getCurrentUser();
        String username = currentUser.getUser().getUsername();
        if (optionalService.lambdaQuery().eq(FinanceOptional::getUsername, username)
                .eq(FinanceOptional::getSymbol, optional.getSymbol()).count() == 0) {
            optional.setUsername(username);
            optionalService.save(optional);
        }
        return BaseResult.success(optional);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "股票取消关注2")
    public BaseResult<?> delete(@RequestParam String symbol) {
        UserVO currentUser = loginUserHolder.getCurrentUser();
        String username = currentUser.getUser().getUsername();
        optionalService.lambdaUpdate()
                .eq(FinanceOptional::getUsername, username)
                .eq(FinanceOptional::getSymbol, symbol)
                .remove();
        return BaseResult.success();
    }
}
