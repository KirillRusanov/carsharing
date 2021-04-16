package carsharing.configuration.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookiesConfigurer {

    @Value("${server.servlet.session.cookie.max-age}")
    private int maxAge;

    public Cookie configureCookie(String token) {
        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        return cookie;
    }
}
