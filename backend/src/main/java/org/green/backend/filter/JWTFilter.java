package org.green.backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.security.CustomUserDetails;
import org.green.backend.utils.CookieUtil;
import org.green.backend.utils.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CookieUtil cookieUtil;

    public JWTFilter(JWTUtil jwtUtil, CookieUtil cookieUtil) {
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = cookieUtil.getCookie(request, "token");
        System.out.println("토큰은 !!!!!: " + authorization);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String id = jwtUtil.getId(token);
        String name = jwtUtil.getName(token);
        String userGbnCd = jwtUtil.getUserGbnCd(token);
        String useYn = jwtUtil.getUseYn(token);

        SecurityUserDto user = new SecurityUserDto();
        user.setId(id);
        user.setPw("비밀번호");
        user.setName(name);
        user.setUserGbnCd(userGbnCd);
        user.setUseYn(useYn);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
