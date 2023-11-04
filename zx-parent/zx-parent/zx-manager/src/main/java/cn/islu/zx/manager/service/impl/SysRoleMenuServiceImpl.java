package cn.islu.zx.manager.service.impl;

import cn.islu.zx.manager.mapper.SysRoleMenuMapper;
import cn.islu.zx.manager.service.SysMenuService;
import cn.islu.zx.manager.service.SysRoleMenuService;
import cn.islu.zx.model.dto.system.AssginMenuDto;
import cn.islu.zx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aloeJun
 * @date 2023/11/4 12:25
 * @description: TODO
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        // 查询所有的菜单数据
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        // 将数据存储到Map中进行返回
        Map<String, Object> result = new HashMap<>();
        result.put("sysMenuList", sysMenuList);
        result.put("roleMenuIds", roleMenuIds);

        // 返回
        return result;
    }

    @Transactional
    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {

        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        // 获取菜单的id
        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if (menuInfo != null && menuInfo.size() > 0) {
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}