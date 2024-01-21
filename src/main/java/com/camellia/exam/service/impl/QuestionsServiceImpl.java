package com.camellia.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellia.exam.context.BaseContext;
import com.camellia.exam.model.dto.questions.QuestionsDTO;
import com.camellia.exam.model.entity.Questions;
import com.camellia.exam.service.QuestionsService;
import com.camellia.exam.mapper.QuestionsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 96055
* @description 针对表【questions】的数据库操作Service实现
* @createDate 2024-01-20 11:30:19
*/
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>
    implements QuestionsService{

    @Resource
    private QuestionsMapper questionsMapper;

    @Override
    public void create(QuestionsDTO questionsDTO) {
        Questions questions = new Questions();
        Date date = new Date();
        BeanUtils.copyProperties(questionsDTO,questions);

        questions.setCreateTime(date);
        questions.setUpdateTime(date);
        questions.setUpdateId(BaseContext.getCurrentId());
        questions.setCreateId(BaseContext.getCurrentId());

        questionsMapper.insert(questions);
    }



    @Override
    public void edit(Long id, QuestionsDTO questionsDTO) {
        Questions questions = new Questions();
        Date date = new Date();
        BeanUtils.copyProperties(questionsDTO,questions);

        questions.setId(id);
        questions.setUpdateId(BaseContext.getCurrentId());
        questions.setUpdateTime(date);

        questionsMapper.updateById(questions);
    }

    @Override
    public void createByMore(List<QuestionsDTO> list) {
        Date date = new Date();
        for (QuestionsDTO questionsDTO : list) {
            Questions questions = new Questions();
            BeanUtils.copyProperties(questionsDTO,questions);
            questions.setCreateTime(date);
            questions.setUpdateTime(date);
            questions.setUpdateId(BaseContext.getCurrentId());
            questions.setCreateId(BaseContext.getCurrentId());
            questionsMapper.insert(questions);
        }
    }
}




