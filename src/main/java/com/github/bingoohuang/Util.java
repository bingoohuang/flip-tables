package com.github.bingoohuang;

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

    public static int displayLength(String s) {
        return s.codePoints().reduce(0,
                (sum, x) -> sum + (Character.UnicodeScript.of(x) == Character.UnicodeScript.HAN ? 2 : 1));
    }

    public static String pad(int width, String data) {
        int realWidth = width - (displayLength(data) - data.length());
        return String.format(" %1$-" + realWidth + "s ", data);
    }
}
