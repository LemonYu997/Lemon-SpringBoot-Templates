package app.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anno")
public class AnnoController {
    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

    // 权限校验：必须具有指定权限才能进入该方法
    @SaCheckPermission("user-add")
    @RequestMapping("add2")
    public String add2() {
        return "用户增加";
    }

    // 二级认证校验：必须二级认证之后才能进入该方法
    @SaCheckSafe()
    @RequestMapping("add3")
    public String add3() {
        return "用户增加";
    }

    // Http Basic 校验：只有通过 Http Basic 认证后才能进入该方法
    @SaCheckHttpBasic(account = "sa:123456")
    @RequestMapping("add4")
    public String add4() {
        return "用户增加";
    }

    // Http Digest 校验：只有通过 Http Digest 认证后才能进入该方法
    @SaCheckHttpDigest(value = "sa:123456")
    @RequestMapping("add5")
    public String add5() {
        return "用户增加";
    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @RequestMapping("send")
    public String send() {
        return "查询用户信息";
    }

    // 注解式鉴权：只要具有其中一个权限即可通过校验
    @RequestMapping("atJurOr")
    @SaCheckPermission(value = {"user-add", "user-all", "user-delete"}, mode = SaMode.OR)
    public SaResult atJurOr() {
        return SaResult.data("用户信息");
    }

    // 角色权限双重 “or校验”：具备指定权限或者指定角色即可通过校验
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        return SaResult.data("用户信息");
    }

    // 此接口加上了 @SaIgnore 可以游客访问
    @SaIgnore
    @RequestMapping("getList")
    public SaResult getList() {
        // ...
        return SaResult.ok();
    }

    // 在 `@SaCheckOr` 中可以指定多个注解，只要当前会话满足其中一个注解即可通过验证，进入方法。
    @SaCheckOr(
            login = @SaCheckLogin,
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("user.add"),
            safe = @SaCheckSafe("update-password"),
            httpBasic = @SaCheckHttpBasic(account = "sa:123456"),
            disable = @SaCheckDisable("submit-orders")
    )
    @RequestMapping("test")
    public SaResult test() {
        // ...
        return SaResult.ok();
    }

    // 当前客户端只要有 [ login 账号登录] 或者 [user 账号登录] 其一，就可以通过验证进入方法。
//         注意：`type = "login"` 和 `type = "user"` 是多账号模式章节的扩展属性，此处你可以先略过这个知识点。
    @SaCheckOr(
            login = { @SaCheckLogin(type = "login"), @SaCheckLogin(type = "user") }
    )
    @RequestMapping("test")
    public SaResult test2() {
        // ...
        return SaResult.ok();
    }

    // 当你在一个方法上写多个注解鉴权时，其默认就是要满足所有注解规则后，才可以进入方法，只要有一个不满足，就会抛出异常
    @SaCheckLogin
    @SaCheckRole("admin")
    @SaCheckPermission("user.add")
    @RequestMapping("test")
    public SaResult test3() {
        // ...
        return SaResult.ok();
    }

    // 测试：只有通过登录校验，或者提供了正确的 ApiKey，才可以进入方法
//    @RequestMapping("/test")
//    @SaCheckOr(login = @SaCheckLogin, append = { SaCheckApiKey.class })
//    @SaCheckApiKey
//    public SaResult test() {
//        // ...
//        return SaResult.ok();
//    }
}
