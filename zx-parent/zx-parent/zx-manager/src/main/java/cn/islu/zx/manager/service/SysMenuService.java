package cn.islu.zx.manager.service;

import cn.islu.zx.model.entity.system.SysMenu;
import cn.islu.zx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findUserMenuList();

}
