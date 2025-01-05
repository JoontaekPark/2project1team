package org.green.frontend.utils;

/**
 * 패키지명        : org.green.frontend.utils
 * 파일명          : DateUtil
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            : Date 유틸
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public String formatYearMonth(LocalDate date) {
        if (date == null) return "";
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public String formatYearMonthDay(LocalDate date) {
        if (date == null) return "";
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String formatYearMonthDayLocalDateTime(LocalDateTime date) {
        if (date == null) return "";
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}