package app.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {
    // 强制指定账号注销下线 ： http://localhost:8081/logout/logout
    @RequestMapping("/logout")
    public SaResult logout() {
        // 踢当前用户
//        StpUtil.logout();
        // 踢指定用户
        StpUtil.logout(10001);
        return SaResult.ok("强制指定账号注销下线");
    }

    // 强制指定账号指定端注销下线  ： http://localhost:8081/logout/logout2
    @RequestMapping("/logout2")
    public SaResult logout2() {
        StpUtil.logout(10001, "PC");
        return SaResult.ok("强制指定账号指定端注销下线");
    }

    //  强制指定 Token 注销下线  ： http://localhost:8081/logout/logoutByTokenValue
    @RequestMapping("/logoutByTokenValue")
    public SaResult logoutByTokenValue() {
        StpUtil.logoutByTokenValue("token");
        return SaResult.ok(" 强制指定 Token 注销下线 ");
    }

    /*
     * 强制注销 和 踢人下线 的区别在于：
        强制注销等价于对方主动调用了注销方法，再次访问会提示：Token无效。
        踢人下线不会清除Token信息，而是将其打上特定标记，再次访问会提示：Token已被踢下线。
     */


    //  将指定账号踢下线   ： http://localhost:8081/logout/kickout
    @RequestMapping("/kickout")
    public SaResult kickout() {
        StpUtil.kickout(10001);
        return SaResult.ok(" 将指定账号踢下线  ");
    }

    //  将指定账号指定端踢下线   ： http://localhost:8081/logout/kickout2
    @RequestMapping("/kickout2")
    public SaResult kickout2() {
        StpUtil.kickout(10001, "PC");
        return SaResult.ok(" 将指定账号指定端踢下线  ");
    }

    //  将指定 Token 踢下线   ： http://localhost:8081/logout/kickoutByTokenValue
    @RequestMapping("/kickoutByTokenValue")
    public SaResult kickoutByTokenValue() {
        StpUtil.kickoutByTokenValue("token");
        return SaResult.ok(" 将指定 Token 踢下线  ");
    }

    //  将指定账号顶下线    ： http://localhost:8081/logout/replaced
    @RequestMapping("/replaced")
    public SaResult replaced() {
        StpUtil.replaced(10001);
        return SaResult.ok(" 将指定账号顶下线 ");
    }
}
