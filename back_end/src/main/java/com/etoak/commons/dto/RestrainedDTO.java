package com.etoak.commons.dto;

import com.etoak.entity.Restrained;
import lombok.Data;

@Data
public class  RestrainedDTO extends Restrained {

    //优势宝可梦的名字
    private String advantageName;

    //劣势宝可梦的名字
    private String inferiorName;
}
