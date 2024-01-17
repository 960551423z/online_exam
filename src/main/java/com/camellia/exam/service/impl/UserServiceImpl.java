package com.camellia.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellia.exam.constant.MessageConstant;
import com.camellia.exam.constant.StatusConstant;
import com.camellia.exam.constant.UserAddConstant;
import com.camellia.exam.constant.UserRoleConstant;
import com.camellia.exam.context.BaseContext;
import com.camellia.exam.exception.AccountLockedException;
import com.camellia.exam.exception.AccountNotFoundException;
import com.camellia.exam.exception.PasswordErrorException;
import com.camellia.exam.mapper.UserMapper;
import com.camellia.exam.model.dto.UserDTO;
import com.camellia.exam.model.dto.UserLoginDTO;
import com.camellia.exam.model.entity.User;
import com.camellia.exam.service.UserService;
import com.camellia.exam.utils.DigestUtilsMD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
* @author 96055
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-16 16:46:28
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;


    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String userAccount = userLoginDTO.getUserAccount();
        String userPassword = userLoginDTO.getUserPassword();

        //1、根据用户名查询数据库中的数据
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserAccount,userAccount);

        // TODO: 判断该账号是不是管理员
        wrapper.eq(User::getUserRole, UserRoleConstant.USER_ADMIN);
        User user = userMapper.selectOne(wrapper);
        log.info("信息：{}",user);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }


        userPassword = DigestUtilsMD5.encryption(userPassword);
        if (!userPassword.equals(user.getUserPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(user.getUserStatus(), StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return user;

    }

    @Override
    public void saveUSer(UserDTO userDTO) {
        User user = new User();
        Date date = new Date();
        // 对象拷贝
        BeanUtils.copyProperties(userDTO,user);

        // 设置剩下的属性
        user.setUserAvatar(UserAddConstant.USER_AVATAR);
        user.setUserPassword(DigestUtilsMD5.encryption(userDTO.getUserPassword()));
        user.setUserStatus(StatusConstant.ENABLE);
        user.setCreateTime(date);

        // 更改id
        user.setUpdateUser(BaseContext.getCurrentId());
        user.setUpdateTime(date);
        userMapper.insert(user);
    }

    @Override
    public void startOrStop(Long status, Long id) {
        User user = new User();
        user.setUserStatus(status);
        user.setUpdateTime(new Date());
        user.setId(id);
        user.setUpdateUser(BaseContext.getCurrentId());
        userMapper.updateById(user);
    }


    @Override
    public void updateUserById(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateUser(BaseContext.getCurrentId());
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        Date date = new Date();
        BeanUtils.copyProperties(userDTO,user);
        user.setUpdateUser(null);
        // TODO: 后台注册默认是admin账号
        user.setUserRole(UserRoleConstant.USER_ADMIN);
        user.setUserPassword(DigestUtilsMD5.encryption(userDTO.getUserPassword()));
        user.setUpdateTime(date);
        user.setCreateTime(date);
        userMapper.insert(user);
    }
}




