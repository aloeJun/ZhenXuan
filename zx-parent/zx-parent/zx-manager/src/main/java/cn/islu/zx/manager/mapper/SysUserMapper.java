package cn.islu.zx.manager.mapper;

import cn.islu.zx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    /**
     * 通过用户名查询用户数据
     *
     * @param userName
     * @return 用户数据
     */
    public abstract SysUser selectByUserName(String userName);
}
