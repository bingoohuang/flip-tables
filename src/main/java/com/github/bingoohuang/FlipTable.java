package com.github.bingoohuang;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * <pre>
 * ┌─────────────┬────────────────────────────┬──────────────┐
 * │ Name        │ Function                   │ Author       │
 * ╞═════════════╪════════════════════════════╪══════════════╡
 * │ Flip Tables │ Pretty-print a text table. │ Jake Wharton │
 * └─────────────┴────────────────────────────┴──────────────┘
 * </pre>
 */
public final class FlipTable {
    private static final String EMPTY = "(empty)";
    private static final String ANSI_COLORS = "\u001B\\[[;\\d]*m";
    public static final String HEAD_START = "┌─┬─┐";
    public static final String HEAD_END = "╞═╧═╡";
    public static final String EMPTY_END = "└───┘";
    public static final String DATA_START = "╞═╪═╡";
    public static final String DATA_SPLIT = "├─┼─┤";
    public static final String DATA_END = "└─┴─┘";
    public static final String BORDER_CHARS = HEAD_START + HEAD_END
            + EMPTY_END + DATA_START + DATA_SPLIT + DATA_END + "│╘═══════╛";


    /**
     * Create a new table with the specified headers and row data.
     *
     * @param headers Header row
     * @param data    Data rows
     * @return a pretty-printed text table
     */
    public static String of(List<String> headers, List<String[]> data) {
        val headerArray = headers.toArray(new String[headers.size()]);
        val dataArray = data.toArray(new String[data.size()][]);
        return FlipTable.of(headerArray, dataArray);
    }

    public static String of(String[] headers, String[][] data) {
        if (headers == null) throw new NullPointerException("headers == null");
        if (headers.length == 0) return "" +
                "┌───────┐\n" +
                "│ Empty │\n" +
                "╘═══════╛\n";

        if (data == null) throw new NullPointerException("data == null");
        return new FlipTable(headers, data).toString();
    }

    private final String[] headers;
    private final String[][] data;
    private final int columns;
    private final int[] columnWidths;
    private final int emptyWidth;

    private FlipTable(String[] headers, String[][] data) {
        this.headers = headers;
        this.data = data;

        columns = headers.length;
        columnWidths = new int[columns];
        for (int row = -1; row < data.length; row++) {
            String[] rowData = (row == -1) ? headers : data[row]; // Hack to parse headers too.
            if (rowData.length != columns) {
                throw new IllegalArgumentException(
                        String.format("Row %s's %s columns != %s columns", row + 1, rowData.length, columns));
            }
            for (int column = 0; column < columns; column++) {
                for (String rowDataLine : rowData[column].split("\\n")) {
                    String rowDataWithoutColor = rowDataLine.replaceAll(ANSI_COLORS, "");
                    columnWidths[column] = Math.max(columnWidths[column], Util.displayLength(rowDataWithoutColor));
                }
            }
        }

        int emptyWidth = 3 * (columns - 1); // Account for column dividers and their spacing.
        for (int columnWidth : columnWidths) {
            emptyWidth += columnWidth;
        }
        this.emptyWidth = emptyWidth;

        if (emptyWidth < EMPTY.length()) { // Make sure we're wide enough for the empty text.
            columnWidths[columns - 1] += EMPTY.length() - emptyWidth;
        }
    }

    // https://en.wikipedia.org/wiki/Box-drawing_character
    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        printDivider(builder, HEAD_START);
        printData(builder, headers);
        if (data.length == 0) {
            printDivider(builder, HEAD_END);
            builder.append('│').append(Util.pad(emptyWidth, EMPTY)).append("│\n");
            printDivider(builder, EMPTY_END);
        } else {
            for (int row = 0; row < data.length; row++) {
                printDivider(builder, row == 0 ? DATA_START : DATA_SPLIT);
                printData(builder, data[row]);
            }
            printDivider(builder, DATA_END);
        }
        return builder.toString();
    }

    private void printDivider(StringBuilder out, String format) {
        for (int column = 0; column < columns; column++) {
            out.append(column == 0 ? format.charAt(0) : format.charAt(2));
            out.append(Util.pad(columnWidths[column], "").replace(' ', format.charAt(1)));
        }
        out.append(format.charAt(4)).append('\n');
    }

    private void printData(StringBuilder out, String[] data) {
        for (int line = 0, lines = 1; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                out.append(column == 0 ? '│' : '│');
                String[] cellLines = data[column].split("\\n");
                lines = Math.max(lines, cellLines.length);
                String cellLine = line < cellLines.length ? cellLines[line] : "";
                out.append(Util.pad(columnWidths[column], cellLine));
            }
            out.append("│\n");
        }
    }


    /**
     * Create a table from an array of objects using {@link String#valueOf}.
     *
     * @param headers Header row
     * @param data    Data rows
     * @return a pretty-printed text table
     */
    public static String of(String[] headers, Object[][] data) {
        if (headers == null) throw new NullPointerException("headers == null");
        if (data == null) throw new NullPointerException("data == null");

        List<String[]> stringData = new ArrayList<>();
        for (Object[] row : data) {
            String[] stringRow = new String[row.length];
            for (int column = 0; column < row.length; column++) {
                stringRow[column] = Util.convertCellData(row[column]);
            }
            stringData.add(stringRow);
        }

        String[][] dataArray = stringData.toArray(new String[stringData.size()][]);
        return FlipTable.of(headers, dataArray);
    }

    /**
     * Create a table from a group of objects. Public accessor methods on the class type with no
     * arguments will be used as the columns.
     *
     * @param rows Iterable rows
     * @return a pretty-printed text table
     */
    public static String of(Iterable<?> rows) {
        if (rows == null) return "" +
                "┌───────┐\n" +
                "│ Null  │\n" +
                "╘═══════╛\n";
        if (Iterables.isEmpty(rows)) return "" +
                "┌───────┐\n" +
                "│ Empty │\n" +
                "╘═══════╛\n";

        Class<?> rowType = findRowType(rows);

        if (rowType == null) {
            return ofAllNulls(rows);
        }

        if (Map.class.isAssignableFrom(rowType)) {
            return fromIterableMaps(rows);
        }
        return fromIterableBeans(rows, rowType);
    }

    private static String ofAllNulls(Iterable<?> rows) {
        List<String> headers = Lists.newArrayList("Value");
        List<String[]> data = new ArrayList<>();
        for (val ignored : rows) {
            String[] rowData = new String[]{"(null)"};
            data.add(rowData);
        }

        return of(headers, data);
    }

    private static Class<?> findRowType(Iterable<?> rows) {
        Class<?> rowType = null;
        for (val row : rows) {
            if (row != null) {
                rowType = row.getClass();
                break;
            }
        }
        return rowType;
    }

    @SuppressWarnings("unchecked")
    private static String fromIterableMaps(Iterable<?> rows) {
        int columnCount = -1;
        List<String> headers = null;
        List<String[]> data = new ArrayList<>();
        for (Object row : rows) {
            Map map = (Map) row;
            if (columnCount < 0) {
                columnCount = map.size();
                headers = Lists.newArrayList(new TreeSet<>(map.keySet()));
            }

            String[] rowData = new String[columnCount];
            for (int i = 0, ii = headers.size(); i < ii; ++i) {
                try {
                    Object object = map.get(headers.get(i));
                    rowData[i] = Util.convertCellData(object);
                } catch (Exception e) {
                    rowData[i] = "err:" + e.getMessage();
                }
            }
            data.add(rowData);
        }

        return of(headers, data);
    }

    private static String fromIterableBeans(Iterable<?> rows, Class<?> rowType) {
        val methods = Util.findGetterMethods(rowType);
        return methods.isEmpty()
                ? fromSingleColumnRows(rows)   // not java bean
                : fromMultipleColumnsRows(rows, methods);
    }

    private static String fromMultipleColumnsRows(Iterable<?> rows, List<Method> methods) {
        int columnCount = methods.size();
        List<String[]> data = new ArrayList<>();
        for (Object row : rows) {
            String[] rowData = new String[columnCount];
            for (int column = 0; column < columnCount; column++) {
                try {
                    Object object = methods.get(column).invoke(row);
                    rowData[column] = Util.convertCellData(object);
                } catch (Exception e) {
                    rowData[column] = "err:" + e.getMessage();
                }
            }
            data.add(rowData);
        }

        List<String> headers = methods.stream()
                .map(x -> {
                    val matcher = Util.METHOD.matcher(x.getName());
                    matcher.matches();
                    return matcher.group(1);
                })
                .collect(Collectors.toList());

        return of(headers, data);
    }

    private static String fromSingleColumnRows(Iterable<?> rows) {
        List<String[]> data = new ArrayList<>();
        for (Object row : rows) {
            String[] rowData = new String[1];
            try {
                rowData[0] = Util.convertCellData(row);
            } catch (Exception e) {
                rowData[0] = "err:" + e.getMessage();
            }

            data.add(rowData);
        }

        return of(new String[]{"Value"}, data.toArray(new String[data.size()][]));
    }

    /**
     * Create a table from a {@link ResultSet}.
     *
     * @param resultSet JDBC ResultSet
     * @return a pretty-printed text table
     */
    @SneakyThrows
    public static String of(ResultSet resultSet) {
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

        return of(headers, data);
    }
}
