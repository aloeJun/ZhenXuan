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
export const SaveSysRole = (sysRole) => {
    return request({
        url: `${sysRole_url}/saveSysRole`,
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