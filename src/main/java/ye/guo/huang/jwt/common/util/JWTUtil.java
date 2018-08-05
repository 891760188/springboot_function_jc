package ye.guo.huang.jwt.common.util;

import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ye.guo.huang.jwt.common.Constant;
import ye.guo.huang.jwt.common.token.Token;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

public class JWTUtil {

    public static Claims decryptJWT(String jsonWebToken, Token token) {
        Claims claim = null;
        try {
            String base64Security = token.getSecurity();
            claim = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security)).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claim;
    }

    /**
     *  加密 行成jwt
     * @param token
     * @return
     */
    public static String encryptJWT(Token token) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签名密钥 就是一个base64加密后的字符串
        String base64Security = token.getSecurity();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT").signWith(signatureAlgorithm, signKey);
        //设置加密信息：目前放在数据库里面，不做考虑
        //setTokenToJwtBuilder(builder, token);
        builder.setAudience(token.getUserId());
        builder.signWith(signatureAlgorithm, signKey);
        // 生成JWT
        System.out.println(decryptJWT(builder.compact(), token).toString());
        return builder.compact();
    }

    private static void setTokenToJwtBuilder(JwtBuilder builder, Token token) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 设置创建时间和token信息
        JsonObject json = new JsonObject();
        json.addProperty("userId", token.getUserId());
        builder.setIssuedAt(now).setSubject(json.toString());
        // 设置个人签名和第三方密钥
        builder.setAudience(token.getUserId());
        // 添加Token过期时间
        if (token.getDeadline() > 0) {
            // 过期时间
            long expMillis = nowMillis + token.getDeadline();
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }
    }


}
