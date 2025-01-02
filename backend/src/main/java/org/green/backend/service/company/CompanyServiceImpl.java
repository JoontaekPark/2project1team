package org.green.backend.service.company;

import lombok.RequiredArgsConstructor;
import org.green.backend.dto.company.*;
import org.green.backend.repository.dao.CompanyDao;
import org.green.backend.utils.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseCompanyDto companyInfo(String companyId, String token) {

        String id = jwtUtil.getId(token);

        ResponseCompanyDto company = companyDao.companyInfo(companyId, id);
        company.setHistories(companyDao.getHistories(companyId));
        company.setJobNotices(companyDao.jobNotices(companyId));

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

    @Override
    public Map<String, Object> companyExtraInfo(String companyId) {
        Map<String, Object> result = new HashMap<>();

        result.put("employees", companyDao.getEmployees(companyId));
        result.put("revenuses", companyDao.getRevenuses(companyId));
        return result;
    }

    @Override
    public List<ResponseJobNoticeDto> getJobNotices(String token) {
        String id = jwtUtil.getId(token);
        return companyDao.jobNotices(id);
    }

    @Override
    public List<StarDto> getStars(String token) {
        String companyId = jwtUtil.getId(token);
        return companyDao.getStars(companyId);
    }

    @Override
    public Map<String, Object> companyMain(int jobNoticeNum) {

        Map<String, Object> result = new HashMap<>();

        result.put("age", companyDao.getAgeByJobNoticeNum(jobNoticeNum));
        result.put("stack", companyDao.getStackByJobNoticeNum(jobNoticeNum));
        result.put("gender", companyDao.getGenderByJobNoticeNum(jobNoticeNum));

        System.out.println(result);

        return result;
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
