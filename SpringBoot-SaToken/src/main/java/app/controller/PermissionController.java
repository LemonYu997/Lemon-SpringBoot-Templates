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
}
