package cn.islu.zx.manager.controller;

import cn.islu.zx.manager.service.SysUserService;
import cn.islu.zx.manager.service.ValidateCodeService;
import cn.islu.zx.model.dto.system.LoginDto;
import cn.islu.zx.model.vo.common.Result;
import cn.islu.zx.model.vo.common.ResultCodeEnum;
import cn.islu.zx.model.vo.system.LoginVo;
import cn.islu.zx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author aloeJun
 * @date 2023/10/14 18:03
 * @description: TODO
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService ;

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo , ResultCodeEnum.SUCCESS) ;
    }
}
