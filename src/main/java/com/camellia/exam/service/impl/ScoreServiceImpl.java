package com.camellia.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellia.exam.model.entity.Score;
import com.camellia.exam.service.ScoreService;
import com.camellia.exam.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

/**
* @author 96055
* @description 针对表【score】的数据库操作Service实现
* @createDate 2024-01-20 11:24:36
*/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService {

}




