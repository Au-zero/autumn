package top.auzero.autumn.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.auzero.autumn.pojo.ResultCode;
import top.auzero.autumn.pojo.ResultVo;
import top.auzero.autumn.utils.EncryptUtils;
import top.auzero.autumn.utils.JwtUtils;
import top.auzero.autumn.utils.RedisUtils;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class SysController {

    private RedisUtils redisUtils;

    @RequestMapping("/ping")
    public ResultVo<?> ping() {
        return ResultVo.data("PONG");
    }

    @RequestMapping("/token")
    public ResultVo<?> token() {
        String id = "111";
        String account = "Auzero";
        String roleId = "1";
        return ResultVo.data(JwtUtils.generate(id, account, roleId));
    }

    @RequestMapping("/verify")
    public ResultVo<?> verify(@RequestHeader String Authorization) {
        return ResultVo.data(JwtUtils.info(Authorization));
    }

    @RequestMapping("/encode")
    public ResultVo<?> encode(String raw) {
        return ResultVo.data(EncryptUtils.encode(raw));
    }

    @RequestMapping("/match")
    public ResultVo<?> match(String raw, String encoded) {
        return ResultVo.data(!EncryptUtils.notMatch(raw, encoded));
    }

    @RequestMapping("/set")
    public ResultVo<?> set(String k, String v) {
        return ResultVo.data(redisUtils.set(k, v));
    }

    @RequestMapping("/get")
    public ResultVo<?> get(String k) {
        return ResultVo.data(redisUtils.get(k));
    }

    @RequestMapping("/del")
    public ResultVo<?> del(String k) {
        return ResultVo.data(redisUtils.del(k));
    }

    @RequestMapping("/exception")
    public Object test() {
        ResultCode.SUCCESS.assertIsFalse(false, "haha");
        return "xixi";
    }

}
