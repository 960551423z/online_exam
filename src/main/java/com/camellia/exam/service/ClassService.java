package com.camellia.exam.service;

import com.camellia.exam.model.dto.classdto.ClassDTO;
import com.camellia.exam.model.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 96055
* @description 针对表【class】的数据库操作Service
* @createDate 2024-01-20 13:45:10
*/
public interface ClassService extends IService<Class> {

    void create(ClassDTO classDTO);

    void edit(Long id, ClassDTO classDTO);
}
