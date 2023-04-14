package com.etoak.mapper;

import com.etoak.commons.dto.PokemenDTO;
import com.etoak.commons.vo.PokemenVO;
import com.etoak.entity.Pokemen;
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
public interface PokemenMapper extends BaseMapper<Pokemen> {

    List<PokemenDTO> listPokemen( PokemenVO pokemenVO);

    int totalPokemen(PokemenVO pokemenVO);

}
