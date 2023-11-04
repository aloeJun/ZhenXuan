package cn.islu.zx.manager.mapper;

import cn.islu.zx.model.dto.system.SysUserDto;
import cn.islu.zx.model.entity.system.SysUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 通过用户名查询用户数据
     *
     * @param userName
     * @return 用户数据
     */
    public abstract SysUser selectByUserName(String userName);

    public abstract List<SysUser> findByPage(SysUserDto sysUserDto);

    public abstract void createSysUser(SysUser sysUser);

    public abstract void updateSysUser(SysUser sysUser);

    public abstract void deleteById(Long userId);
}
