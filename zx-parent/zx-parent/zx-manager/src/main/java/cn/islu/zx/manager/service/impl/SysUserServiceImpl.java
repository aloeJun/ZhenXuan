package cn.islu.zx.manager.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.islu.zx.manager.mapper.SysUserMapper;
import cn.islu.zx.manager.service.SysUserService;
import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.entity.system.SysUser;
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
    private RedisTemplate redisTemplate;


    @Override
    public LoginVo login(LoginDto loginDto) {
        // 获取用户
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());


        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 用户存在  比对输入密码是否与redis一直
        String inputPassword = loginDto.getPassword();
        String md5InputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        System.out.println(md5InputPassword);

        System.out.println(sysUser.getPassword());

        if (!md5InputPassword.equals(sysUser.getPassword())) {
            throw new RuntimeException("密码错误");
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
