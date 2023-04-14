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
public class Restrained implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优势属性id
     */
    private Integer advantage;

    /**
     * 劣势属性id
     */
    private Integer inferiorposition;

    /**
     * 战斗力偏移量
     */
    private Integer offset;


}
