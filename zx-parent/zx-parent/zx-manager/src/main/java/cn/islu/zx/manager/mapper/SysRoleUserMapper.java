package cn.islu.zx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {

    public abstract void doAssign(@Param("userId") Long userId,
                                  @Param("roleId") Long roleId);		// 添加关联关系
    public abstract void deleteByUserId(Long userId);				// 根据用户的id删除数据

    List<Long> findSysUserRoleByUserId(Long userId);
}