package com.camellia.exam.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.camellia.exam.commont.PageResult;
import com.camellia.exam.commont.Result;
import com.camellia.exam.constant.DeleteConstant;
import com.camellia.exam.constant.MsgSuccessConstant;
import com.camellia.exam.model.dto.classdto.ClassDTO;
import com.camellia.exam.model.dto.classdto.ClassPageDTO;
import com.camellia.exam.model.entity.Class;
import com.camellia.exam.model.vo.ClassVO;
import com.camellia.exam.service.ClassService;
import com.camellia.exam.utils.ReturnInfoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 阿庆
 * @Date: 2024/1/20 16:37
 */
@RestController
@RequestMapping("/admin/class")
@Slf4j
@Api(tags = "班级相关接口")
public class ClassController {
    @Autowired
    private ClassService classService;


    /**
     * 新增班级
     * @param classDTO
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "新增班级")
    public Result save(@RequestBody ClassDTO classDTO) {
        log.info("新增班级: {}",classDTO);
        classService.create(classDTO);
        return Result.success(MsgSuccessConstant.ADD_SUCCESS);
    }

    /**
     * 根据id进行修改
     * @param id
     * @param classDTO
     * @return
     */
    @PutMapping("/edit/{id}")
    @ApiOperation(value = "修改班级")
    public Result edit(@PathVariable Long id,@RequestBody ClassDTO classDTO) {
        log.info("修改班级参数: {} {}",id,classDTO);
        classService.edit(id,classDTO);
        return Result.success(MsgSuccessConstant.EDIT_SUCCESS);
    }


    /**
     * 班级删除，逻辑删除
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "删除班级")
    public Result delete(@PathVariable Long id) {
        log.info("删除的id: {}",id);
        UpdateWrapper<Class> wrapper = new UpdateWrapper<>();
        classService.update(wrapper.lambda().eq(Class::getId,id).set(Class::getIsDelete, DeleteConstant.DISABLE));
        return Result.success();
    }

    /**
     * 班级分页
     * @param queryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "班级分页查询")
    public Result<PageResult> page(ClassPageDTO queryDTO) {
        log.info("分页查询参数：{}",queryDTO);
        Page<Class> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());

        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(StringUtils.isNotBlank(queryDTO.getClassName()),Class::getClassName,queryDTO.getClassName());
        Page<Class> page = classService.page(pageInfo, wrapper);


        List<Class> records = page.getRecords();
        List<ClassVO> convert = ReturnInfoUtils.convert(records, ClassVO.class);
        return Result.success(new PageResult(records.size(),convert));

    }
}
