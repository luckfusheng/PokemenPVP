package com.etoak.controller;


import com.etoak.commons.dto.RestrainedDTO;
import com.etoak.commons.vo.ResultVO;
import com.etoak.service.RestrainedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
@RestController
@RequestMapping("/restrained")
public class RestrainedController {

    @Autowired
    private RestrainedService restrainedService;

    @RequestMapping("/{pid}")
    public ResultVO getRestrained(@PathVariable("pid") int pid){
        List<RestrainedDTO> restraineds = restrainedService.getRestrainedByPid(pid);
        return restraineds!=null ? ResultVO.success(restraineds):ResultVO.failed("查询失败");
    }

    @RequestMapping("/fight")
    public ResultVO fight(@RequestParam("lid") int lid,@RequestParam("rid") int rid ){
        return ResultVO.success(restrainedService.fight(lid,rid));
    }
}

