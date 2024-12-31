package org.green.backend.service.company;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.company.CompanyDto;
import org.green.backend.repository.dao.CompanyDao;
import org.springframework.stereotype.Service;

/**
 * 패키지명        : org.green.backend.service.company
 * 파일명          : CompanyService
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    @Override
    public void save(CompanyDto company) {
        companyDao.save(company);
    }
}
