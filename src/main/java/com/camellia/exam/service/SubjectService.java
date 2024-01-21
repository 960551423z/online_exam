package com.camellia.exam.service;

import com.camellia.exam.model.dto.subject.SubjectDTO;
import com.camellia.exam.model.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 96055
* @description 针对表【Subject】的数据库操作Service
* @createDate 2024-01-20 11:21:01
*/
public interface SubjectService extends IService<Subject> {

    void create(SubjectDTO subjectDTO);

    void edit(Long id, SubjectDTO subjectDTO);
}
