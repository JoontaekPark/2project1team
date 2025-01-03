package org.green.backend.service.common;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.FileDto;
import org.green.backend.dto.common.SignInDto;
import org.green.backend.dto.common.UserDto;
import org.green.backend.dto.company.CompanyDto;
import org.green.backend.dto.company.ResponseJobNoticeDto;
import org.green.backend.repository.dao.common.UserDao;
import org.green.backend.security.CustomUserDetails;
import org.green.backend.service.company.CompanyService;
import org.green.backend.utils.FileUploadUtil;
import org.green.backend.utils.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    private final CompanyService companyService;


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

        if (user.getUserGbnCd().equals("20")){
            CompanyDto company = new CompanyDto();
            company.setId(user.getId());
            companyService.save(company);
        }

        if (user.getProfile() != null && !user.getProfile().isEmpty()) {
            fileService.saveFile(user.getProfile(), "10", user.getId(), user.getId());
        }

        return result;
    }

    @Override
    @Transactional
    public int edit(UserDto user, boolean fileChk) throws IOException {

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
    @Transactional
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
    @Transactional
    public UserDto userInfo(String token) throws Exception {

        if (token == null || token.isEmpty()) {
            throw new Exception("토큰이 빈값입니다.");
        }

        String id = jwtUtil.getId(token);
        String fileGbnCd = "10";

        UserDto user = userDao.findById(id);

        FileDto file = fileService.getFile(fileGbnCd, id);
        user.setFileDto(file);
        return user;

    }

    @Override
    public Map<String, Object> userMain(String token) throws Exception {

        Map<String, Object> result = new HashMap<>();

        String id = "";
        System.out.println(token);
        if (token != null) {
            id = jwtUtil.getId(token);
        }
        System.out.println(id);
        List<ResponseJobNoticeDto> likeJobNotices = userDao.getLikeJobNotices(id);
        List<ResponseJobNoticeDto> popJobNotices = userDao.getPopJobNotices(id);
        List<ResponseJobNoticeDto> shortJobNotices = userDao.getShortJobNotices(id);

        System.out.println(likeJobNotices);
        System.out.println(popJobNotices);
        System.out.println(shortJobNotices);

        result.put("likeJobNotices", likeJobNotices);
        result.put("popJobNotices", popJobNotices);
        result.put("shortJobNotices", shortJobNotices);

        return result;
    }

}
