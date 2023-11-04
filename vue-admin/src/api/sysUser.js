import request from '@/utils/request'

const sysUser_url = "/admin/system/sysUser"

// 分页查询角色数据
export const GetSysUserListByPage = (pageNum , pageSize , queryDto) => {
    return request({
        url: `${sysUser_url}/findByPage/${pageNum}/${pageSize}`,
        method: 'get',
        data: queryDto
    })
}


// 添加角色
export const CreateSysUser = (sysUser) => {
    return request({
        url: `${sysUser_url}/createSysUser`,
        method: 'post',
        data:sysUser
    })
}

// 角色修改
export const UpdateSysUser = (sysUser) => {
    return request({
        url: `${sysUser_url}/updateSysUser`,
        method: 'put',
        data:sysUser
    })
}

// 角色删除
export const DeleteSysUserById = (userId) => {
    return request({
        url: `${sysUser_url}/deleteById/${userId}`,
        method: 'delete'
    })
}

// 给用户分配角色请求
export const DoAssignRoleToUser = (assginRoleVo) => {
    return request({
        url: "/admin/system/sysUser/doAssign",
        method: 'post',
        data: assginRoleVo
    })
}