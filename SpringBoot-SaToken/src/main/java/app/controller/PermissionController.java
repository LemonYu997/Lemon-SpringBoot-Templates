package app.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    // 获取：当前账号所拥有的权限集合，浏览器访问： http://localhost:8081/permission/getList
    @RequestMapping("/getList")
    public SaResult getList() {
        StpUtil.checkLogin();
        return SaResult.ok("当前账号所拥有的权限集合：" + StpUtil.getPermissionList());
    }

    // 判断：当前账号是否含有指定权限, 返回 true 或 false，浏览器访问： http://localhost:8081/permission/hasPermission
    @RequestMapping("/hasPermission")
    public SaResult hasPermission() {
        StpUtil.checkLogin();
        return SaResult.ok("当前账号是否含有指定权限：" + StpUtil.hasPermission("user.add"));
    }

    // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
    // 浏览器访问： http://localhost:8081/permission/checkPermission
    @RequestMapping("/checkPermission")
    public SaResult checkPermission() {
        StpUtil.checkLogin();
        StpUtil.checkPermission("user.add");
        return SaResult.ok("当前账号是否含有指定权限");
    }

    // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过] http://localhost:8081/permission/checkPermissionAnd
    @RequestMapping("/checkPermissionAnd")
    public SaResult checkPermissionAnd() {
        StpUtil.checkLogin();
        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");
        return SaResult.ok("当前账号是否含有指定权限");
    }

    // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可] http://localhost:8081/permission/checkPermissionOr
    @RequestMapping("/checkPermissionOr")
    public SaResult checkPermissionOr() {
        StpUtil.checkLogin();
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");
        return SaResult.ok("当前账号是否含有指定权限");
    }

    // 获取：当前账号所拥有的角色集合，浏览器访问： http://localhost:8081/permission/getRoleList
    @RequestMapping("/getRoleList")
    public SaResult getRoleList() {
        StpUtil.checkLogin();
        return SaResult.ok("当前账号所拥有的角色集合：" + StpUtil.getRoleList());
    }

    // 判断：当前账号是否拥有指定角色, 返回 true 或 false  http://localhost:8081/permission/hasRole
    @RequestMapping("/hasRole")
    public SaResult hasRole() {
        StpUtil.checkLogin();
        return SaResult.ok("当前账号是否拥有指定角色：" + StpUtil.hasRole("super-admin"));
    }

    // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException http://localhost:8081/permission/checkRole
    @RequestMapping("/checkRole")
    public SaResult checkRole() {
        StpUtil.checkLogin();
        StpUtil.checkRole("super-admin");
        return SaResult.ok("当前账号是否含有指定角色标识");
    }
}
