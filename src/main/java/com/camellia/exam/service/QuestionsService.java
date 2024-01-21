package com.camellia.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camellia.exam.model.dto.questions.QuestionsDTO;
import com.camellia.exam.model.entity.Questions;

import java.util.List;

/**
* @author 96055
* @description 针对表【questions】的数据库操作Service
* @createDate 2024-01-20 11:30:19
*/
public interface QuestionsService extends IService<Questions> {

    void create(QuestionsDTO questionsDTO);

    void edit(Long id, QuestionsDTO questionsDTO);

    void createByMore(List<QuestionsDTO> list);
}
