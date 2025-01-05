package org.green.backend.service.admin;

import java.util.List;
import java.util.Map;

/**
 * 패키지명        : org.green.backend.service.admin
 * 파일명          : AdminService
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

public interface AdminService {

    public Map<String, Object> adminMain(int page, String search, String filter);

    public void changeStatus(List<String> ids);

}
