package org.green.backend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.dto.common.SignInDto;
import org.green.backend.security.CustomUserDetails;
import org.green.backend.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : SignInServiceImpl
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 로그인 서비스 구현체
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public String signIn(SignInDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getId(),
                        dto.getPw()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtUtil.createJwt(
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getUserGbnCd(),
                userDetails.getUseYn(),
                24 * 60 * 60 * 1000L
        );

        return token;
    }

}
