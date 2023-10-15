package cn.islu.zx.manager.service;

import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.entity.system.SysUser;
import cn.islu.zx.model.vo.system.LoginVo;

public interface SysUserService {
    /**
     * 根据用户名查询用户数据
     *
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);

    public abstract SysUser getUserInfo(String token);
}
