package org.green.backend.service.company;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.company.CompanyDto;
import org.green.backend.dto.company.EmployeeDto;
import org.green.backend.dto.company.HistoryDto;
import org.green.backend.dto.company.RevenusDto;
import org.green.backend.repository.dao.CompanyDao;
import org.green.backend.utils.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final JWTUtil jwtUtil;

    @Override
    public CompanyDto getCompany(String token) {

        String id = jwtUtil.getId(token);

        CompanyDto company = companyDao.get(id);
        company.setRevenuse(companyDao.getRevenuses(id));
        company.setHistory(companyDao.getHistories(id));
        company.setEmployee(companyDao.getEmployees(id));

        return company;
    }

    @Override
    public void save(CompanyDto company) {
        companyDao.save(company);
    }

    @Override
    public void update(CompanyDto company, String token) {

        String id = jwtUtil.getId(token);
        company.setId(id);

        companyDao.update(company);
        insertRevenue(company.getRevenuse(), id);
        insertHistory(company.getHistory(), id);
        insertEmployee(company.getEmployee(), id);
    }

//    매출액 저장
    private void insertRevenue(List<RevenusDto> revenus, String id) {

        companyDao.deleteRevenue(id);

        for (RevenusDto revenusDto : revenus) {
            revenusDto.setId(id);

            companyDao.saveRevenue(revenusDto);
        }

    }

    private void insertHistory(List<HistoryDto> history, String id) {

        companyDao.deleteHistory(id);

        for (HistoryDto historyDto : history) {
            historyDto.setId(id);

            companyDao.saveHistory(historyDto);
        }
    }

    private void insertEmployee(List<EmployeeDto> employee, String id) {

        companyDao.deleteEmployee(id);

        for (EmployeeDto employeeDto : employee) {
            employeeDto.setId(id);

            companyDao.saveEmployee(employeeDto);
        }
    }

}
