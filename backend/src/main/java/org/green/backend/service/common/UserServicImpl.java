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
    private final JWTUtil jwtUtil;


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

    @Override
    public int edit(UserDto user, boolean fileChk) throws IOException {

        System.out.println(user.getPw());

        user.setPw(bCryptPasswordEncoder.encode(user.getPw()));

        int result = userDao.edit(user);

        if (fileChk) {

            fileService.deleteAllFiles("10", user.getId());

            if (user.getProfile() != null && !user.getProfile().isEmpty()) {
                fileService.saveFile(user.getProfile(), user.getUserGbnCd(), user.getId(), user.getId());
            }
        }

        return result;
    }

    @Override
    public int checkPw(String token, String pw) {

        String id = jwtUtil.getId(token);
        String resultPw = userDao.checkPw(id);

        if (pw == null || resultPw == null) {
            throw new IllegalArgumentException("PW를 입력해주세요.");
        }

        if (!bCryptPasswordEncoder.matches(pw, resultPw)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return 1;
    }

    @Override
    public UserDto userInfo(String token) throws Exception {

        if (token == null || token.isEmpty()) {
            throw new Exception("토큰이 빈값입니다.");
        }

        String id = jwtUtil.getId(token);

        return userDao.findById(id);
    }

}
