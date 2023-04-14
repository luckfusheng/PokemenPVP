package com.etoak.controller;


import com.etoak.commons.vo.ResultVO;
import com.etoak.entity.Attributes;
import com.etoak.service.AttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/attributes")
public class AttributesController {

    @Autowired
    AttributesService attributesService;

    @RequestMapping("/list")
    public ResultVO list(){
        List<Attributes> attributesList = attributesService.list();
        return attributesList !=null && attributesList.size()!=0?
                ResultVO.success(attributesList):ResultVO.failed("查询失败");
    }

}

