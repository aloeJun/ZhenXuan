import request from '@/utils/request'

const sysRole_url = '/admin/system/sysMenu'
// 分页列表
export const FindNodes = () => {
    return request({
        url: `${sysRole_url}/findNodes`,
        method: 'get',
    })
}

// 保存信息
export const SaveMenu = sysMenu => {
    return request({
        url: `${sysRole_url}/save`,
        method: 'post',
        data: sysMenu,
    })
}

// 修改信息
export const UpdateSysMenuById = sysMenu => {
    return request({
        url: `${sysRole_url}/updateById`,
        method: 'put',
        data: sysMenu,
    })
}

// 根据id删除数据
export const RemoveSysMenuById = id => {
    return request({
        url: `${sysRole_url}/removeById/${id}`,
        method: 'delete',
    })
}

// 获取菜单
export const GetMenus = params => {
    return request({
      url: '/admin/system/index/menus',
      method: 'get',
      params,
    })
  }