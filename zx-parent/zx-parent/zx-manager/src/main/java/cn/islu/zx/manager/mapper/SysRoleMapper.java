package cn.islu.zx.manager.mapper;

import cn.islu.zx.model.dto.system.SysRoleDto;
import cn.islu.zx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper {
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    public abstract void createSysRole(SysRole sysRole);

    public abstract void updateSysRole(SysRole sysRole);

    public abstract void deleteById(Long roleId);

    List<SysRole> findAllRoles();
    
}
