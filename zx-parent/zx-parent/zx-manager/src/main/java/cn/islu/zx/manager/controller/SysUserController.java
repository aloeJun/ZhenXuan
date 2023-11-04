package cn.islu.zx.manager.controller;

import cn.islu.zx.manager.service.SysUserService;
import cn.islu.zx.model.dto.system.AssginRoleDto;
import cn.islu.zx.model.dto.system.SysUserDto;
import cn.islu.zx.model.entity.system.SysUser;
import cn.islu.zx.model.vo.common.Result;
import cn.islu.zx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloeJun
 * @date 2023/10/21 12:29
 * @description: TODO
 */
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserService.doAssign(assginRoleDto) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    // 用户条件分类查询
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(SysUserDto sysUserDto,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
    // 用户添加接口
    @PostMapping(value = "/createSysUser")
    public Result createSysUser(@RequestBody SysUser sysUser){
        sysUserService.createSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    // 用户修改接口
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser){
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    // 用户删除接口
    @DeleteMapping(value = "/deleteById/{userId}")
    public Result deleteByID(@PathVariable Long userId){
        sysUserService.deleteById(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
