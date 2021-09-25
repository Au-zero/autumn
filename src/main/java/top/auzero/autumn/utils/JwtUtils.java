package top.auzero.autumn.utils;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import top.auzero.autumn.pojo.ResultVo;
import top.auzero.autumn.pojo.TokenInfo;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
*
* @author Auzero
* @since 2021/9/24
*/
public class JwtUtils {

    private static final long TOKEN_EXPIRED_TIME = 30L * 24 * 60 * 60 * 1000;

    private static final String JWT_SECRET = "dG9wLmF1emVyby5vdXJjaXR5LmF1dHVtbkJ5QXV6ZXJv";

    /**
     * 创建JWT
     * @param user 用户信息
     * @param expTime JWT过期时间
     * @return token String
     */
    public static String create(Map<String, String> user, Long expTime) {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");

        JwtBuilder jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
//                .setIssuer("Auzero")
                .setClaims(user)
//                .setAudience(user.get("uid") + "")
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .setIssuedAt(new Date())
                .setId(user.get("id") + "")
                .signWith(key);

        return jwt.compact();
    }

    public static String generate(String id, String account, String roleId) {
        Map<String, String> user = new HashMap<>();
        user.put("id", id);
        user.put("account", account);
        user.put("roleId", roleId);

        return create(user, TOKEN_EXPIRED_TIME);
    }

    /**
     * 验证token
     * @param token token
     * @return ResultVo
     */
    public static Map<String, String> verifyToken(String token) {
        token = token.split("Bearer ")[1];
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET)
                    .build()
                    .parseClaimsJws(token);

            Map<String, String> map = new HashMap<>();
            map.put("id", (String) jws.getBody().get("id"));
            map.put("account", (String) jws.getBody().get("account"));
            map.put("roleId", (String) jws.getBody().get("roleId"));
            return map;
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * 得到token中的信息
     * @param token jwt
     * @return TokenInfo
     */
    public static TokenInfo info(String token) {
        Map<String, String> map = verifyToken(token);
        //////////assertIsNotNull
        TokenInfo tokenInfo = new TokenInfo();
        assert map != null;
        tokenInfo.setId(map.get("id"));
        tokenInfo.setAccount(map.get("account"));
        tokenInfo.setRoleId(map.get("roleId"));

        return tokenInfo;
    }

}
