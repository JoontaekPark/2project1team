package org.green.frontend.service.admin;

import java.util.Map;
import java.util.Objects;

/**
 * 패키지명        : org.green.frontend.service.admin
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

    public Map<String, Object> adminMain(int page, String search, String filter) throws Exception;

}
