package com.etoak.controller;

import cn.hutool.core.util.IdUtil;
import com.etoak.commons.properties.ImageProperties;
import com.etoak.commons.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    ImageProperties imageProperties;

    @PostMapping("/image")
    public ResultVO uploadImage(MultipartFile file) {


        // 判断文件对象是否为空
        if (file.isEmpty()) return ResultVO.failed("文件不能为空");
        // 判断
        if (!imageProperties.getTypes().contains(file.getContentType())) {
            String message = imageProperties.getTypes()
                    .stream()
                    .map(type -> type.substring(type.lastIndexOf("/") + 1))
                    .collect(Collectors.joining("、"));
            return ResultVO.failed("仅支持" + message + "类型");
        }

        // 修改文件名
        String originalFilename = file.getOriginalFilename();
        String filename = StringUtils.join(IdUtil.simpleUUID(),
                originalFilename.substring(originalFilename.lastIndexOf(".")));
        // 创建保存文件夹
        File uploadDir = new File(imageProperties.getDir());
        if(!uploadDir.exists()) uploadDir.mkdirs();
        // 保存文件
        try {
            file.transferTo(new File(uploadDir,filename));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return ResultVO.failed("文件上传失败");
        }
        String path = imageProperties.getPrefix().endsWith("/")?
                imageProperties.getPrefix()+filename:
                imageProperties.getPrefix()+"/"+filename;
        return ResultVO.success(path);
    }

}
