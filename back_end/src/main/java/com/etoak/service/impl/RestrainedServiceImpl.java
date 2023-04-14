package com.etoak.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.commons.dto.RestrainedDTO;
import com.etoak.entity.Attributes;
import com.etoak.entity.Pokemen;
import com.etoak.entity.Restrained;
import com.etoak.mapper.PandaMapper;
import com.etoak.mapper.PokemenMapper;
import com.etoak.mapper.RestrainedMapper;
import com.etoak.service.PandaService;
import com.etoak.service.PokemenService;
import com.etoak.service.RestrainedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 后端查询两个宝可梦的属性ID，然后从属性克制关系表中有克制关系吗，
 把查到的结果记录下来，并修改战力，
 返回给前端有克制关系的属性和修改之后的战力
 */
@Service
public class RestrainedServiceImpl extends ServiceImpl<RestrainedMapper, Restrained> implements RestrainedService {

    @Autowired
    RestrainedMapper restrainedMapper;

    @Autowired
    PandaMapper pandaMapper;

    @Autowired
    PokemenMapper pokemenMapper;

    @Override
    public List<RestrainedDTO> getRestrainedByPid(int pid) {
        return restrainedMapper.getRestrainedByPid(pid);
    }


    @Override
    public Map<String, Object> fight(int lid, int rid) {
        Map<String, Object> resultMap = new HashMap<String, Object>();//返回的结果
        List<RestrainedDTO> llist = new ArrayList<RestrainedDTO>();//左宝可梦对右宝可梦的克制关系
        List<RestrainedDTO> rlist = new ArrayList<RestrainedDTO>();//右宝可梦对左宝可梦的克制关系

        List<Integer> lAttributes = pandaMapper.getAttributesByPid(lid);//左宝可梦属性id集合
        List<Integer> rAttributes = pandaMapper.getAttributesByPid(rid);//右宝可梦属性id集合

        lAttributes.forEach(la->{
            for (Integer ra : rAttributes) {
                RestrainedDTO restrainedDTO = restrainedMapper.getByAdvantageInferior(la, ra);//查左对右有克制吗
                if (restrainedDTO != null) {
                    llist.add(restrainedDTO);
                }
                RestrainedDTO restrainedDTO2 = restrainedMapper.getByAdvantageInferior(ra, la);//查右对左有克制吗
                if (restrainedDTO2 != null) {
                    rlist.add(restrainedDTO2);
                }
            }
        });

        Pokemen lpokemen = pokemenMapper.selectById(lid);//查左宝可梦信息
        Pokemen rpokemen = pokemenMapper.selectById(rid);//查右宝可梦信息
        if(llist!=null && llist.size()!=0){//判断是否有能克制的属性
            llist.forEach(item ->{
                lpokemen.setSs(lpokemen.getSs() + item.getOffset());
            });
        }
        if(rlist!=null && rlist.size()!=0){
            rlist.forEach(item ->{
                rpokemen.setSs(rpokemen.getSs() + item.getOffset());
            });
        }

        resultMap.put("lplayer",llist);
        resultMap.put("rplayer",rlist);
        resultMap.put("ss",new int[]{lpokemen.getSs(),rpokemen.getSs()});//修改后的战力

        return resultMap;
    }
}
