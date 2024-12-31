package org.green.frontend.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 패키지명        : org.green.frontend.utils
 * 파일명          : NumberUtil
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

public class NumberUtil {
    public static String formatWithComma(int number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }
}
