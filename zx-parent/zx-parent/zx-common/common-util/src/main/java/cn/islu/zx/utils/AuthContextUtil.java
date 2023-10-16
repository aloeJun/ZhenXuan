package cn.islu.zx.utils;

import cn.islu.zx.model.entity.system.SysUser;

/**
 * @author aloeJun
 * @date 2023/10/16 9:47
 * @description: 对ThreadLocal进行封装
 */
public class AuthContextUtil {

    // 创建一个Threadload对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    // 添加数据
    public static void set(SysUser sysUser){
        threadLocal.set(sysUser);
    }
    // 获取数据
    public static SysUser get(){
        return threadLocal.get();
    }

    // 删除数据
    public static void remove(){
        threadLocal.remove();
    }
}