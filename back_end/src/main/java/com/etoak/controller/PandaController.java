package com.etoak.controller;


import com.etoak.service.PandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
@RestController
@RequestMapping("/panda")
public class PandaController {

    @Autowired
    private PandaService pandaService;


}

