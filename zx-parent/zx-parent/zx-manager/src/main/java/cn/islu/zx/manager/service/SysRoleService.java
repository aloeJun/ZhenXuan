package cn.islu.zx.manager.service;

import cn.islu.zx.model.dto.system.SysRoleDto;
import cn.islu.zx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService {
    public abstract PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void createSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> finAllRoles();

    Map<String, Object> findAllRoles(Long userId);
}
