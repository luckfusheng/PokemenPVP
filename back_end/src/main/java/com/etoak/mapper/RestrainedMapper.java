package com.etoak.mapper;

import com.etoak.commons.dto.RestrainedDTO;
import com.etoak.entity.Restrained;
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
public interface RestrainedMapper extends BaseMapper<Restrained> {

    List<RestrainedDTO> getRestrainedByPid(int pid);
    RestrainedDTO getByAdvantageInferior(@Param("aid") int aid,@Param("iid") int iid);
}
