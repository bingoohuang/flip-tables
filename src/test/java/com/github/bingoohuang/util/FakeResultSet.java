package com.github.bingoohuang.util;

import lombok.RequiredArgsConstructor;

import java.sql.ResultSetMetaData;

@RequiredArgsConstructor
public final class FakeResultSet extends AbstractResultSet {
    private final String[] headers;
    private final String[][] data;
    private int row = -1;

    @Override public ResultSetMetaData getMetaData() {
        return new FakeResultSetMetaData();
    }

    @Override public boolean isBeforeFirst() {
        return row == -1;
    }

    @Override public boolean next() {
        return ++row < data.length;
    }

    @Override public String getString(int columnIndex) {
        return data[row][columnIndex - 1];
    }

    private class FakeResultSetMetaData extends AbstractResultSetMetaData {
        @Override public int getColumnCount() {
            return headers.length;
        }

        @Override public String getColumnName(int column) {
            return headers[column - 1];
        }
    }
}
