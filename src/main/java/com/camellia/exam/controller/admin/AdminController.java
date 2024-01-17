package com.camellia.exam.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.camellia.exam.commont.PageResult;
import com.camellia.exam.commont.Result;
import com.camellia.exam.constant.JwtClaimsConstant;
import com.camellia.exam.constant.MsgSuccessConstant;
import com.camellia.exam.context.BaseContext;
import com.camellia.exam.model.dto.UserDTO;
import com.camellia.exam.model.dto.UserLoginDTO;
import com.camellia.exam.model.dto.UserPageQueryDTO;
import com.camellia.exam.model.entity.User;
import com.camellia.exam.model.vo.UserLoginVO;
import com.camellia.exam.properties.JwtProperties;
import com.camellia.exam.service.UserService;
import com.camellia.exam.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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
        userService.register(userDTO);
        return Result.success(MsgSuccessConstant.REGISTER_SUCCESS);
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
        return Result.success(new PageResult(page.getTotal(), page.getRecords()));
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


    @PutMapping()
    @ApiOperation(value = "修改用户信息")
    public Result update(@RequestBody UserDTO userDTO) {
        log.info("编辑用户信息 {}",userDTO);
        userService.updateUserById(userDTO);
        return Result.success(MsgSuccessConstant.UPDATE_SUCCESS);
    }
}
