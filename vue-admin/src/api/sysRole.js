import request from '@/utils/request'

const sysRole_url = "/admin/system/sysRole"

// 分页查询角色数据
export const GetSysRoleListByPage = (pageNum , pageSize , queryDto) => {
    return request({
        url: `${sysRole_url}/findByPage/${pageNum}/${pageSize}`,
        method: 'post',
        data: queryDto
    })
}

// 添加角色
export const CreateSysRole = (sysRole) => {
    return request({
        url: `${sysRole_url}/createSysRole`,
        method: 'post',
        data:sysRole
    })
}

// 角色修改
export const UpdateSysRole = (sysRole) => {
    return request({
        url: `${sysRole_url}/updateSysRole`,
        method: 'put',
        data:sysRole
    })
}

// 角色删除
export const DeleteSysRoleById = (roleId) => {
    return request({
        url: `${sysRole_url}/deleteById/${roleId}`,
        method: 'delete'
    })
}

// 查询所有的角色数据
export const GetAllRoleList = (userId) => {
    return request({
        url: '/admin/system/sysRole/findAllRoles/' + userId,
        method: 'get'
    })
}

// 查询指定角色所对应的菜单id
export const GetSysRoleMenuIds = (roleId) => {
    return request({
        url: "/admin/system/sysRoleMenu/findSysRoleMenuByRoleId/"+ roleId,
        method: 'get'
    })
}

// 根据角色分配菜单请求方法
export const DoAssignMenuIdToSysRole = (assignMenuDto) => {
    return request({
        url: "/admin/system/sysRoleMenu/doAssign",
        method: 'post',
        data: assignMenuDto
    })
}