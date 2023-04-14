package com.etoak.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.commons.dto.PokemenDTO;
import com.etoak.commons.vo.PageVO;
import com.etoak.commons.vo.PokemenVO;
import com.etoak.entity.Pokemen;
import com.etoak.mapper.PandaMapper;
import com.etoak.mapper.PokemenMapper;
import com.etoak.service.PokemenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemenServiceImpl extends ServiceImpl<PokemenMapper, Pokemen> implements PokemenService {

    @Autowired
    private PokemenMapper pokemenMapper;

    @Autowired
    private PandaMapper pandaMapper;

    @Transactional
    @Override
    public boolean insert(PokemenVO pokemenVO) {
        int count = pokemenMapper.insert(pokemenVO);
        this.pokemenAttributeHandler(pokemenVO);
        count+= pandaMapper.addPandas(pokemenVO.getAttributesLists(),pokemenVO.getId());
        return pokemenVO.getId()!=null && count>=2;
    }

    @Override
    public PageVO<PokemenDTO> list(int pageNumber, int pageSize, PokemenVO pokemenVO) {
        PageHelper.startPage(pageNumber, pageSize);
        List<PokemenDTO> listPokemen = pokemenMapper.listPokemen(pokemenVO);
        PageInfo<PokemenDTO> info = new PageInfo<>(listPokemen);
        int total = pokemenMapper.totalPokemen(pokemenVO);
//        info.setPageSize(total);
//        return new PageVO<>(pageNumber,
//                pageSize,
//                listPokemen,
//                total);
        return new PageVO<>(info.getPageNum(),
                                    info.getPageSize(),
                                    listPokemen,
                                    info.getTotal());
    }
    @Transactional
    @Override
    public boolean update(PokemenVO pokemenVO) {
        int count = pokemenMapper.updateById(pokemenVO);
        this.pokemenAttributeHandler(pokemenVO);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pokemenVO.getId());
        count += pandaMapper.delete(queryWrapper);
        count += pandaMapper.addPandas(pokemenVO.getAttributesLists(),pokemenVO.getId());
        return pokemenVO.getId()!=null && count>=3;
    }

    private void pokemenAttributeHandler(PokemenVO pokemenVO)  {
        String[] split = new String[0];
        if(pokemenVO.getAttributes()!=null)
            split = pokemenVO.getAttributes().split(",");
        List<Integer> attributeList = new ArrayList<Integer>();
        for (String s : split) {
            attributeList.add(Integer.parseInt(s));
        }
        pokemenVO.setAttributesLists(attributeList);
    }

}
