package org.green.backend.service;

import org.green.backend.repository.dao.JobNoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Service
public class JobNoticeService {

    @Autowired
    private JobNoticeDao jobNoticeDao;


}
