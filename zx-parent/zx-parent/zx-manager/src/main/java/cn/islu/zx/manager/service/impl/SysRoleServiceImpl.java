package cn.islu.zx.manager.service.impl;

import cn.islu.zx.manager.mapper.SysRoleMapper;
import cn.islu.zx.manager.service.SysRoleService;
import cn.islu.zx.model.dto.system.SysRoleDto;
import cn.islu.zx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aloeJun
 * @date 2023/10/18 10:16
 * @description: TODO
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

        @Autowired
        private SysRoleMapper sysRoleMapper ;

        @Override
        public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
            PageHelper.startPage(pageNum , pageSize) ;
            List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto) ;
            PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList) ;
            return pageInfo;
        }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole) ;
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId) ;
    }
}
