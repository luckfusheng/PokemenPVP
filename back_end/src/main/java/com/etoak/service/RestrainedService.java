package com.etoak.service;

import com.etoak.commons.dto.RestrainedDTO;
import com.etoak.entity.Restrained;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.mapper.RestrainedMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
public interface RestrainedService extends IService<Restrained> {

//    得到克制关系通过宝可梦的id
    List<RestrainedDTO> getRestrainedByPid(int pid);

    //拿到对战结果 根据做宝可梦和右宝可梦
    Map<String,Object> fight(int lid, int rid);
}
