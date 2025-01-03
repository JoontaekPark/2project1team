package org.green.backend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.LikeDto;
import org.green.backend.repository.dao.common.LikeDao;
import org.green.backend.utils.JWTUtil;
import org.springframework.stereotype.Service;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : LikeServiceImpl
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final JWTUtil jwtUtil;
    private final LikeDao likeDao;

    @Override
    public void like(String token, LikeDto like) {

        String id = jwtUtil.getId(token);
        like.setInstId(id);

        likeDao.delete(like);

        if (like.isLikedStatus()){
            return;
        }

        likeDao.save(like);
    }
}
