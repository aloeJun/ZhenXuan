package cn.islu.zx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.islu.zx.common.exception.DefineException;
import cn.islu.zx.manager.mapper.SysRoleUserMapper;
import cn.islu.zx.manager.mapper.SysUserMapper;
import cn.islu.zx.manager.service.SysUserService;
import cn.islu.zx.model.dto.system.AssginRoleDto;
import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.dto.system.SysUserDto;
import cn.islu.zx.model.entity.system.SysUser;
import cn.islu.zx.model.vo.common.ResultCodeEnum;
import cn.islu.zx.model.vo.system.LoginVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
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

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

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
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void createSysUser(SysUser sysUser) {
        // 根据输入的用户名查询用户
        SysUser dbSysUser = sysUserMapper.selectByUserName(sysUser.getUserName());
        if (dbSysUser != null) {
            throw new DefineException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        // 对密码进行加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(0);
        sysUserMapper.createSysUser(sysUser);
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        System.out.println(sysUser.getAvatar());
        sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }

    @Transactional
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {

        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        // 分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        roleIdList.forEach(roleId -> {
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(), roleId);
        });
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
