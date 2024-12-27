package org.green.backend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.repository.dao.common.UserDao;
import org.springframework.stereotype.Service;

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

    private final UserDao userDao;

    @Override
    public int checkId(String id) {
        return userDao.checkId(id);
    }
}
