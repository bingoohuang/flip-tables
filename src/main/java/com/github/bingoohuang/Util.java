package com.github.bingoohuang;

import com.google.common.collect.Lists;
import lombok.val;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Util {
    public static String convertCellData(Object object) {
        return object == null ? "(null)"

                : object instanceof DateTime
                ? ((DateTime) object).toString("yyyy-MM-dd HH:mm:ss")

                : object instanceof LocalDate
                ? ((LocalDate) object).toString("yyyy-MM-dd")

                : object instanceof LocalTime
                ? ((LocalTime) object).toString("HH:mm:ss")

                : object.toString();
    }


    public static final Pattern METHOD = Pattern.compile("^(?:get|is|has)([A-Z][a-zA-Z0-9]*)+$");

    public static List<Method> findGetterMethods(Class<?> rowType) {
        if (CharSequence.class.isAssignableFrom(rowType)) {
            return Lists.newArrayList();
        }

        val allMethods = rowType.getMethods();
        Arrays.sort(allMethods, Comparator.comparing(Method::getName));

        List<Method> methods = new ArrayList<>();
        for (Method m : allMethods) {
            if (m.isSynthetic()) continue;
            if (Modifier.isStatic(m.getModifiers())) continue;
            if (m.getDeclaringClass() == Object.class) continue;
            if (m.getParameterTypes().length > 0) continue;
            if (m.getReturnType() == void.class) continue;
            if (!METHOD.matcher(m.getName()).matches()) continue;

            m.setAccessible(true);
            methods.add(m);
        }
        return methods;
    }


    public static String pad(int width, String data) {
        int realWidth = width - (displayLength(data) - data.length());
        return String.format(" %1$-" + realWidth + "s ", data);
    }


    /**
     * 是否半角字符，仅支持判断半角的符号、英文字母和数字，日文暂不支持.
     * 金山词霸的解释是：
     * <p>
     * SBC case —— 全角
     * DBC case —— 半角
     * <p>
     * 我目前知道的是：
     * <p>
     * SBCS —— single byte character set
     * DBCS —— double byte character set
     * MBCS —— multi-byte character set
     * <p>
     *
     * @param c char
     * @return SBC or not
     */
    public static boolean isDBC(char c) {
        // 基本拉丁字母（即键盘上可见的，空格、数字、字母、符号）or 日文半角片假名和符号
        return c >= 32 && c <= 127 || c >= 65377 && c <= 65439;
    }


    /**
     * 字符串长度取得（区分半角、全角）
     *
     * @param str 字符串
     * @return 字符串长度
     */
    public static int displayLength(String str) {
        int len = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            len += FlipTable.BORDER_CHARS.indexOf(ch) >= 0 || isDBC(ch) ? /* 半角*/ 1 : 2;
        }
        return len;
    }
}
