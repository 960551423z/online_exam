package com.camellia.exam.service;

import com.camellia.exam.model.dto.user.UserDTO;
import com.camellia.exam.model.dto.user.UserLoginDTO;
import com.camellia.exam.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 96055
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-16 16:46:28
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 管理员后台新增员工
     * @param userDTO
     */
    void saveUSer(UserDTO userDTO);

    /**
     * 禁用启用账号
     * @param status
     * @param id
     */
    void startOrStop(Long status, Long id);


    void updateUserById(UserDTO userDTO);

    void register(UserDTO userDTO);
}
