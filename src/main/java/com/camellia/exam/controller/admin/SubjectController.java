package com.camellia.exam.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.camellia.exam.commont.PageResult;
import com.camellia.exam.commont.Result;
import com.camellia.exam.constant.DeleteConstant;
import com.camellia.exam.constant.MsgSuccessConstant;
import com.camellia.exam.model.dto.subject.SubjectDTO;
import com.camellia.exam.model.dto.subject.SubjectPageDTO;
import com.camellia.exam.model.entity.Subject;
import com.camellia.exam.model.vo.SubjectVO;
import com.camellia.exam.service.SubjectService;
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
 * @Date: 2024/1/20 13:28
 */

@RestController
@RequestMapping("/admin/subject")
@Slf4j
@Api(tags = "学科相关接口")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 学科创编
     *
     * @param subjectDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "学科创建")
    public Result save(@RequestBody SubjectDTO subjectDTO) {
        log.info("新增学科：{}", subjectDTO);
        subjectService.create(subjectDTO);
        return Result.success(MsgSuccessConstant.ADD_SUCCESS);
    }


    /**
     * 学科分页
     *
     * @param queryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "学科分页查询")
    public Result<PageResult> page(SubjectPageDTO queryDTO) {
        log.info("分页查询的参数：{}", queryDTO);

        LambdaQueryWrapper<Subject> wrapper
                = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(queryDTO.getSubName()), Subject::getSubName, queryDTO.getSubName())
                .like(StringUtils.isNotBlank(queryDTO.getGrade()), Subject::getGrade, queryDTO.getGrade());

        Page<Subject> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        Page<Subject> page = subjectService.page(pageInfo, wrapper);


        List<Subject> records = page.getRecords();
        List<SubjectVO> convert = ReturnInfoUtils.convert(records, SubjectVO.class);

        return Result.success(new PageResult(convert.size(), convert));
    }


    /**
     * 学科删除，逻辑删除
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "学科删除")
    public Result remove(@PathVariable Long id) {
        log.info("删除的id: {}",id);
        LambdaUpdateWrapper<Subject> updateWrapper
                = new LambdaUpdateWrapper<>();

        updateWrapper.eq(Subject::getId,id).set( Subject::getIsDelete, DeleteConstant.DISABLE);

        subjectService.update(updateWrapper);
        return Result.success();
    }


    /**
     * 学科编辑
     * @param id
     * @param subjectDTO
     * @return
     */
    @PutMapping("/edit/{id}")
    @ApiOperation(value = "学科编辑")
    public Result edit(@PathVariable Long id,@RequestBody SubjectDTO subjectDTO) {
        subjectService.edit(id,subjectDTO);
        return Result.success(MsgSuccessConstant.EDIT_SUCCESS);
    }


}
