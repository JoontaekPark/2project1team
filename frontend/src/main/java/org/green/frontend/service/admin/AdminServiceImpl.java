package org.green.frontend.service.admin;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.company.CompanyDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 패키지명        : org.green.frontend.service.admin
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
public class AdminServiceImpl implements AdminService {


    private final WebClientUtil webClientUtil;

    @Override
    public Map<String, Object> adminMain(int page, String search, String filter) throws Exception {

        String baseUrl = "/api/v1/admin";

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?page=").append(page);

        if (search != null && !search.isEmpty()) {
            urlBuilder.append("&search=").append(search);
        }

        if (filter != null && !filter.isEmpty()) {
            urlBuilder.append("&filter=").append(filter);
        }

        // Convert StringBuilder to String
        String url = urlBuilder.toString();

        ApiResponse<Map> result = webClientUtil.getApi(url, Map.class);

        return result.getBody();
    }
}
