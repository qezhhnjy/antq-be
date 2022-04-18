package com.qezhhnjy.antq.web.controller.im;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.enums.ResultCode;
import com.qezhhnjy.antq.common.exception.BizException;
import com.qezhhnjy.antq.common.vo.im.GroupVO;
import com.qezhhnjy.antq.service.im.GroupService;
import com.qezhhnjy.antq.web.holder.LoginUserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-15:01
 */
@RestController
@Slf4j
@RequestMapping("/im-group")
public class GroupController {

    @Resource
    private GroupService    groupService;
    @Resource
    private LoginUserHolder loginUserHolder;

    @PostMapping("/add")
    public BaseResult<?> add(@RequestBody @Valid GroupVO groupVO) {
        if (groupVO.getUserList().size() < 3) throw new BizException(ResultCode.ILLEGAL_ARG, "群组成员不能小于3个");
        groupService.add(groupVO);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult<List<GroupVO>> list() {
        List<GroupVO> result = groupService.list(loginUserHolder.getCurrentUser().getUser());
        return BaseResult.success(result);
    }
}
