package cn.islu.zx.manager.mapper;

import cn.islu.zx.model.dto.system.SysRoleDto;
import cn.islu.zx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    public abstract void saveSysRole(SysRole sysRole);

    public abstract void updateSysRole(SysRole sysRole);

    public abstract void deleteById(Long roleId);
}
