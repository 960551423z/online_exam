package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellia.exam.mapper.QuestionsMapper;
import com.camellia.exam.model.entity.Questions;
import generator.service.QuestionsService;
import org.springframework.stereotype.Service;

/**
* @author 96055
* @description 针对表【questions(试题)】的数据库操作Service实现
* @createDate 2024-01-21 10:54:34
*/
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>
    implements QuestionsService{

}




