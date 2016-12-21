package com.blogging.auth.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by pulla6 on 20/12/16.
 */
public class TokenGenerator {

    public static String generate(String email) throws Exception {
        try {
            Date expiry = new Date();
            String token = JWT.create()
                    .withIssuer("blogger")
                    .withClaim("email", email)
                    .withExpiresAt(DateUtils.addHours(expiry, 2))
                    .sign(Algorithm.HMAC256("secret"));
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new Exception("Exception while generating token");
        }
    }
}
