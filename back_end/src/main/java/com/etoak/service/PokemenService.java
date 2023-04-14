package com.etoak.service;

import com.etoak.commons.dto.PokemenDTO;
import com.etoak.commons.vo.PageVO;
import com.etoak.commons.vo.PokemenVO;
import com.etoak.entity.Pokemen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
public interface PokemenService extends IService<Pokemen> {

    boolean insert(PokemenVO pokemenVO);
    PageVO<PokemenDTO> list(int pageNumber,int pageSize,PokemenVO pokemenVO);
    boolean update(PokemenVO pokemenVO);
}
