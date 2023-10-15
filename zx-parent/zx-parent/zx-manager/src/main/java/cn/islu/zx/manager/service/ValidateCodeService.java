package cn.islu.zx.manager.service;

import cn.islu.zx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    /**
     * 获取图片验证码
     * @return
     */
    public abstract ValidateCodeVo generateValidateCode();
}
