package com.camellia.exam.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.camellia.exam.commont.PageResult;
import com.camellia.exam.commont.Result;
import com.camellia.exam.constant.DeleteConstant;
import com.camellia.exam.constant.JwtClaimsConstant;
import com.camellia.exam.constant.MessageConstant;
import com.camellia.exam.constant.MsgSuccessConstant;
import com.camellia.exam.model.dto.user.UserDTO;
import com.camellia.exam.model.dto.user.UserLoginDTO;
import com.camellia.exam.model.dto.user.UserPageQueryDTO;
import com.camellia.exam.model.entity.Subject;
import com.camellia.exam.model.entity.User;
import com.camellia.exam.model.vo.UserLoginVO;
import com.camellia.exam.model.vo.UserVO;
import com.camellia.exam.properties.EmailProperties;
import com.camellia.exam.properties.JwtProperties;
import com.camellia.exam.service.UserService;
import com.camellia.exam.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 13:56
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
@Api(tags = "用户登录接口")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;



    /**
     * 登录
     *
     * @param userLoginDTO 封装对象，接收前端发送的值
     * @return TODO：验证码登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录操作")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userAccount(user.getUserAccount())
                .userName(user.getUserName())
                .token(token)
                .build();

        return Result.success(userLoginVO,MsgSuccessConstant.LOGIN_SUCCESS);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "退出登录操作")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result register(@RequestBody UserDTO userDTO) {

        // 判断邮箱格式
        if (Boolean.FALSE.equals(RegexUtils.checkEmail(userDTO.getUserAccount()))) {
            return Result.error(MessageConstant.EMAIL_ERROR);
        }

        // 从redis获取验证码，若存在判断请求的验证码，则register，否则返回 "无效的验证码"
        Object code = RedisUtils.get(redisTemplate, userDTO.getUserAccount());
        Integer inpCode = userDTO.getCode();
        if (code == null) {
            return Result.error(MessageConstant.CODE_EXPIRATION_ERROR);
        } else if (inpCode == null || !inpCode.equals(code)) {
            return Result.error(MessageConstant.CODE_ERROR);
        } else {
            userService.register(userDTO);
            return Result.success(MsgSuccessConstant.REGISTER_SUCCESS);
        }

    }




    @PostMapping("/sendEmail")
    @ApiOperation(value = "发送邮件")
    public Result email(String userAccount) {

        Integer rangeCode = EmailUtils.rangeCode();
        // 判断邮箱格式
        if (Boolean.FALSE.equals(RegexUtils.checkEmail(userAccount))) {
            return Result.error(MessageConstant.EMAIL_ERROR);
        }

        Object code = RedisUtils.get(redisTemplate,userAccount);
        if (code != null) {
            return Result.error(MessageConstant.CODE_EMAIL_ERROR);
        }

        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserAccount, userAccount));
        if (one != null) {
            return Result.error(MessageConstant.ALREADY_EXISTS);
        }

        EmailUtils.send(emailProperties,userAccount,rangeCode);
        RedisUtils.set(redisTemplate,userAccount,rangeCode);
        return Result.success(MsgSuccessConstant.EMAIL_SUCCESS);
    }

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "新增用户")
    public Result<String> save(@RequestBody UserDTO userDTO) {
        log.info("新增用户： {}", userDTO);
        userService.saveUSer(userDTO);
        return Result.success(MsgSuccessConstant.ADDUSER_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return
     */
    @GetMapping("/page/")
    @ApiOperation(value = "用户分页查询")
    public Result<PageResult> page(UserPageQueryDTO queryDTO) {
        log.info("分页查询的参数：{}", queryDTO);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(queryDTO.getUserAccount()),User::getUserAccount, queryDTO.getUserAccount())
                .like(StringUtils.isNotBlank(queryDTO.getUserName()),User::getUserName, queryDTO.getUserName());

        Page<User> pageInfo = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        Page<User> page = userService.page(pageInfo, wrapper);

        List<User> records = page.getRecords();

        List<UserVO> convert = ReturnInfoUtils.convert(records, UserVO.class);

        return Result.success(new PageResult(convert.size(),convert));
    }

    /**
     * 启用或禁用用户账号
     *
     * @param status 状态
     * @param id     修改人 id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用禁用用户账号")
    public Result startOrStop(@PathVariable Long status, Long id) {
        log.info("启用禁用用户账号: {},{}", status, id);
        userService.startOrStop(status, id);
        return Result.success(MsgSuccessConstant.UPDATE_SUCCESS);
    }


    /**
     * 修改用户信息
     * @param userDTO
     * @return
     */
    @PutMapping()
    @ApiOperation(value = "修改用户信息")
    public Result update(@RequestBody UserDTO userDTO) {
        log.info("编辑用户信息 {}",userDTO);
        userService.updateUserById(userDTO);
        return Result.success(MsgSuccessConstant.UPDATE_SUCCESS);
    }


    /**
     * 删除用户。逻辑删除
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "用户删除")
    public Result remove(@PathVariable Long id) {
        log.info("删除的id: {}",id);
        LambdaUpdateWrapper<User> updateWrapper
                = new LambdaUpdateWrapper<>();

        updateWrapper.eq(User::getId,id).set(User::getIsDelete, DeleteConstant.DISABLE);

        userService.update(updateWrapper);
        return Result.success();
    }


}
