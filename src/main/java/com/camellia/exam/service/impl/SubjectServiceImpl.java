package com.camellia.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellia.exam.context.BaseContext;
import com.camellia.exam.model.dto.subject.SubjectDTO;
import com.camellia.exam.model.entity.Subject;
import com.camellia.exam.service.SubjectService;
import com.camellia.exam.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author 96055
* @description 针对表【Subject】的数据库操作Service实现
* @createDate 2024-01-20 11:21:01
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService {


    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void create(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        Date date = new Date();
        BeanUtils.copyProperties(subjectDTO,subject);
        subject.setCreateTime(date);
        subject.setUpdateTime(date);
        subject.setUpdateId(BaseContext.getCurrentId());

        subjectMapper.insert(subject);
    }

    @Override
    public void edit(Long id, SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        Date date = new Date();
        subject.setId(id);
        BeanUtils.copyProperties(subjectDTO,subject);
        subject.setUpdateTime(date);
        subject.setUpdateId(BaseContext.getCurrentId());
        subjectMapper.updateById(subject);
    }
}




