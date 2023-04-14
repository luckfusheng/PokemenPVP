package com.etoak.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.commons.dto.PokemenDTO;
import com.etoak.commons.vo.PageVO;
import com.etoak.commons.vo.PokemenVO;
import com.etoak.commons.vo.ResultVO;
import com.etoak.entity.Pokemen;
import com.etoak.service.PandaService;
import com.etoak.service.PokemenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ET2211
 * @since 2023-04-07
 */
@RestController
@RequestMapping("/pokemen")
public class PokemenController {

    @Autowired
    private PokemenService pokemenService;

    @Autowired
    private PandaService pandaService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO addPokemen(@RequestBody PokemenVO pokemenVO){
        return pokemenService.insert(pokemenVO) ? ResultVO.success("添加成功") : ResultVO.failed("添加失败");
    }

    @RequestMapping("/list")
    public ResultVO listPokemen(@RequestParam(name = "pageNumber",required = false,defaultValue = "1") int pageNumber,
                                @RequestParam(name = "pageSize",required = false,defaultValue = "4") int pageSize,
                                @RequestBody(required = false) PokemenVO pokemenVO){
        PageVO<PokemenDTO> listPokemen = pokemenService.list(pageNumber, pageSize, pokemenVO);
        return listPokemen!=null && listPokemen.getRows()!=null && listPokemen.getRows().size()>0 ?
                ResultVO.success(listPokemen):ResultVO.failed("失败");
    }

    @RequestMapping("/update")
    public ResultVO updatePokemen(@RequestBody PokemenVO pokemenVO){
        return pokemenService.update(pokemenVO)? ResultVO.success("修改成功") : ResultVO.failed("修改失败");
    }

    @RequestMapping("/delete")
    public ResultVO deletePokemen(int id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",id);
        pandaService.remove(queryWrapper);
        return pokemenService.removeById(id)? ResultVO.success("删除成功") : ResultVO.failed("删除失败");
    }
}

