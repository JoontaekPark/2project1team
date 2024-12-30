package org.green.backend.service.common;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.FileDto;
import org.green.backend.dto.common.SignInDto;
import org.green.backend.dto.common.UserDto;
import org.green.backend.repository.dao.common.UserDao;
import org.green.backend.security.CustomUserDetails;
import org.green.backend.utils.FileUploadUtil;
import org.green.backend.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : UserServicImpl
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class UserServicImpl implements UserService {

    private final FileService fileService;
    private final FileUploadUtil fileUploadUtil;
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public int checkId(String id) {
        return userDao.checkId(id);
    }

    @Override
    @Transactional
    public int save(UserDto user) throws IOException {

        user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
        user.setUseYn("Y");

        int result = userDao.save(user);

        if (user.getProfile() != null && !user.getProfile().isEmpty()) {
            fileService.saveFile(user.getProfile(), user.getUserGbnCd(), user.getId(), user.getId());
        }

        return result;
    }
}
