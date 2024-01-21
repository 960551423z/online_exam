package com.camellia.exam.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.camellia.exam.commont.PageResult;
import com.camellia.exam.commont.Result;
import com.camellia.exam.constant.DeleteConstant;
import com.camellia.exam.constant.MsgSuccessConstant;
import com.camellia.exam.model.dto.classdto.ClassDTO;
import com.camellia.exam.model.dto.questions.QuestionsDTO;
import com.camellia.exam.model.dto.questions.QuestionsPageDTO;
import com.camellia.exam.model.entity.Class;
import com.camellia.exam.model.entity.Questions;
import com.camellia.exam.model.vo.QuestionVO;
import com.camellia.exam.service.QuestionsService;
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
 * @Date: 2024/1/21 10:37
 * 试题库
 */

@RestController
@Api(tags = "题目相关接口")
@RequestMapping("/admin/questions")
@Slf4j
public class QuestionsController {

    // TODO: 题目分页查询
    // TODO: 试试题目，答案能否乱序
    // TODO： 随机出题

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("/radio")
    @ApiOperation(value = "单选题新增")
    public Result saveRadio(@RequestBody QuestionsDTO questionsDTO) {
        // 新增一道题试试
        questionsService.create(questionsDTO);
        return Result.success();
    }

    // TODO: 后期再考虑优化分页,还有展示
    @GetMapping("/page")
    @ApiOperation(value = "题目分页")
    public Result<PageResult> page(QuestionsPageDTO queryDTO) {
        Page<Questions> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        LambdaQueryWrapper<Questions> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(queryDTO.getTitle()),Questions::getTitle,queryDTO.getTitle())
                        .eq(queryDTO.getQuesType() != null,Questions::getQuesType,queryDTO.getQuesType())
                                .like(queryDTO.getSubjectId() != null,Questions::getSubjectId,queryDTO.getSubjectId())
                                        .eq(queryDTO.getScore() != null,Questions::getScore,queryDTO.getScore())
                                                .eq(queryDTO.getDegree() != null,Questions::getDegree,queryDTO.getDegree());

        Page<Questions> page = questionsService.page(pageInfo, wrapper);
        List<Questions> records =
                page.getRecords();

        List<QuestionVO> convert = ReturnInfoUtils.convert(records, QuestionVO.class);

        return Result.success(new PageResult(convert.size(),convert));
    }


    /**
     * 题目删除，逻辑删除
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "删除题目")
    public Result delete(@PathVariable Long id) {
        log.info("删除的id: {}",id);
        UpdateWrapper<Questions> wrapper = new UpdateWrapper<>();
        questionsService.update(wrapper.lambda().eq(Questions::getId,id).set(Questions::getIsDelete, DeleteConstant.DISABLE));
        return Result.success();
    }

    /**
     * 根据id进行修改
     * @param id
     * @param questionsDTO
     * @return
     */
    @PutMapping("/edit/{id}")
    @ApiOperation(value = "修改题目")
    public Result edit(@PathVariable Long id,@RequestBody QuestionsDTO questionsDTO) {
        log.info("修改题目参数: {} {}",id,questionsDTO);
        questionsService.edit(id,questionsDTO);
        return Result.success(MsgSuccessConstant.EDIT_SUCCESS);
    }
    // TODO: 一次添加多个题目
    @PostMapping("/radios")
    @ApiOperation(value = "新增多个题目")
    public Result saveRadios(@RequestBody List<QuestionsDTO> list) {
        questionsService.createByMore(list);
        return Result.success();
    }
}
