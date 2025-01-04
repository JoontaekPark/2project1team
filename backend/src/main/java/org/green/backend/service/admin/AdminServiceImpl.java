package org.green.backend.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.admin.ResponseUserDto;
import org.green.backend.dto.common.CodeInfoDto;
import org.green.backend.repository.dao.AdminDao;
import org.green.backend.service.common.CodeInfoService;
import org.green.backend.utils.Paging;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 패키지명        : org.green.backend.service.admin
 * 파일명          : AdminServiceImpl
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final CodeInfoService codeInfoService;

    @Override
    public Map<String, Object> adminMain(int page,
                                         String search,
                                         String filter) {

        Map<String, Object> map = new HashMap<String, Object>();

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        int totalCount = totalCount(search, filter);
        List<ResponseUserDto> list = listPaging(offset, pageSize, search, filter);

        Paging paging = totalCount > 0
                ? new Paging(totalCount, page, pageSize, 10)
                : new Paging(0, 1, pageSize, 10);

        List<CodeInfoDto> userGbnList = codeInfoService.getCodeInfos("user_gbn_cd");

        map.put("list", list);
        map.put("paging", paging);
        map.put("userGbnList", userGbnList);

        return map;
    }

    @Override
    public void changeStatus(List<String> ids) {
        for (String id : ids) {
            adminDao.status(id);
        }
    }

    private int totalCount(String search, String filter) {
        return adminDao.userCnt(search, filter);
    }

    private List<ResponseUserDto> listPaging(int offset,
                                      int limit,
                                      String search,
                                      String filter) {

        return adminDao.userList(offset, limit, search, filter);
    }
}
