package com.jakewharton.fliptables;

import lombok.SneakyThrows;
import lombok.val;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper methods for creating {@link FlipTable tables} from various different data sets.
 */
public final class FlipTableConverters {
    private static final Pattern METHOD = Pattern.compile("^(?:get|is|has)([A-Z][a-zA-Z0-9]*)+$");

    /**
     * Create a table from an array of objects using {@link String#valueOf}.
     */
    public static String fromObjects(String[] headers, Object[][] data) {
        if (headers == null) throw new NullPointerException("headers == null");
        if (data == null) throw new NullPointerException("data == null");

        List<String[]> stringData = new ArrayList<>();
        for (Object[] row : data) {
            String[] stringRow = new String[row.length];
            for (int column = 0; column < row.length; column++) {
                stringRow[column] = convertCellData(row[column]);
            }
            stringData.add(stringRow);
        }

        String[][] dataArray = stringData.toArray(new String[stringData.size()][]);
        return FlipTable.of(headers, dataArray);
    }

    /**
     * Create a table from a group of objects. Public accessor methods on the class type with no
     * arguments will be used as the columns.
     */
    public static String fromIterable(Iterable<?> rows, Class<?> rowType) {
        if (rows == null) throw new NullPointerException("rows == null");
        if (rowType == null) throw new NullPointerException("rowType == null");

        Method[] declaredMethods = rowType.getMethods();
        Arrays.sort(declaredMethods, Comparator.comparing(Method::getName));

        List<Method> methods = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        for (Method method : declaredMethods) {
            if (method.isSynthetic()) continue;
            if (method.getDeclaringClass() == Object.class) continue;
            if (method.getParameterTypes().length > 0) continue;
            if (method.getReturnType() == void.class) continue;
            if (Modifier.isStatic(method.getModifiers())) continue;

            Matcher matcher = METHOD.matcher(method.getName());
            if (!matcher.matches()) continue;

            method.setAccessible(true);
            methods.add(method);
            headers.add(matcher.group(1));
        }

        int columnCount = methods.size();
        List<String[]> data = new ArrayList<>();
        for (Object row : rows) {
            String[] rowData = new String[columnCount];
            for (int column = 0; column < columnCount; column++) {
                try {
                    Object object = methods.get(column).invoke(row);
                    rowData[column] = convertCellData(object);
                } catch (Exception e) {
                    rowData[column] = "err:" + e.getMessage();
                }
            }
            data.add(rowData);
        }

        String[] headerArray = headers.toArray(new String[headers.size()]);
        String[][] dataArray = data.toArray(new String[data.size()][]);
        return FlipTable.of(headerArray, dataArray);
    }

    private static String convertCellData(Object object) {
        if (object == null) {
            return "(null)";
        } else if (object instanceof DateTime) {
            return ((DateTime) object).toString("yyyy-MM-dd HH:mm:ss");
        } else if (object instanceof LocalDate) {
            return ((LocalDate) object).toString("yyyy-MM-dd");
        } else if (object instanceof LocalTime) {
            return ((LocalTime) object).toString("HH:mm:ss");
        } else {
            return object.toString();
        }
    }

    /**
     * Create a table from a {@link ResultSet}.
     */
    @SneakyThrows
    public static String fromResultSet(ResultSet resultSet) {
        if (resultSet == null) throw new NullPointerException("resultSet == null");
        if (!resultSet.isBeforeFirst()) throw new IllegalStateException("Result set not at first.");

        List<String> headers = new ArrayList<>();
        val resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int column = 0; column < columnCount; column++) {
            headers.add(resultSetMetaData.getColumnName(column + 1));
        }

        List<String[]> data = new ArrayList<>();
        while (resultSet.next()) {
            String[] rowData = new String[columnCount];
            for (int column = 0; column < columnCount; column++) {
                rowData[column] = resultSet.getString(column + 1);
            }
            data.add(rowData);
        }

        String[] headerArray = headers.toArray(new String[headers.size()]);
        String[][] dataArray = data.toArray(new String[data.size()][]);
        return FlipTable.of(headerArray, dataArray);
    }
}
