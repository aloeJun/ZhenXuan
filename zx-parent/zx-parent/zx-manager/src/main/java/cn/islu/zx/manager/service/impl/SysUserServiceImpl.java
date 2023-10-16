package cn.islu.zx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.islu.zx.common.exception.DefineException;
import cn.islu.zx.manager.mapper.SysUserMapper;
import cn.islu.zx.manager.service.SysUserService;
import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.entity.system.SysUser;
import cn.islu.zx.model.vo.common.ResultCodeEnum;
import cn.islu.zx.model.vo.system.LoginVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author aloeJun
 * @date 2023/10/14 18:07
 * @description: TODO
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }

    @Override
    public LoginVo login(LoginDto loginDto) {

        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key

        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);

        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
            throw new DefineException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:login:validatecode:" + codeKey);

        // 获取用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());


        if (sysUser == null) {
            throw new DefineException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 用户存在  比对输入密码是否与redis一直
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());

        if (!md5InputPassword.equals(sysUser.getPassword())) {
            throw new DefineException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(sysUser), 30, TimeUnit.DAYS);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;

    }
}
