package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pokemen implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 战斗力
     */
    private Integer ss;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 身高
     */
    private String stature;

    /**
     * 体重
     */
    private String weight;

    /**
     * 特征
     */
    private String characteristic;


}
