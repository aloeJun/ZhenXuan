package cn.islu.zx.manager.service;

import cn.islu.zx.model.dto.system.AssginRoleDto;
import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.dto.system.SysUserDto;
import cn.islu.zx.model.entity.system.SysUser;
import cn.islu.zx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {
    /**
     * 根据用户名查询用户数据
     *
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto);

    public abstract SysUser getUserInfo(String token);

    public abstract void logout(String token);

    public abstract PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    public abstract void createSysUser(SysUser sysUser);

    public abstract void updateSysUser(SysUser sysUser);

    public abstract void deleteById(Long userId);

    public abstract void doAssign(AssginRoleDto assginRoleDto);
}
