package com.etoak.mapper;

import com.etoak.entity.Attributes;
import com.etoak.entity.Panda;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
public interface PandaMapper extends BaseMapper<Panda> {
    List<Integer> getAttributesByPid(@Param("pid") int pid);
    int addPandas(@Param("aids") List<Integer> aids,@Param("pid") int pid);
}
