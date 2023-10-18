package cn.islu.zx.manager.service;

import cn.islu.zx.model.dto.system.SysRoleDto;
import cn.islu.zx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {
    public abstract PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);
}
