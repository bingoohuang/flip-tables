package com.jakewharton.fliptables.util;

import java.sql.ResultSetMetaData;

public abstract class AbstractResultSetMetaData implements ResultSetMetaData {
    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException();
    }

    @Override public int getColumnCount() {
        throw unsupported();
    }

    @Override public boolean isAutoIncrement(int column) {
        throw unsupported();
    }

    @Override public boolean isCaseSensitive(int column) {
        throw unsupported();
    }

    @Override public boolean isSearchable(int column) {
        throw unsupported();
    }

    @Override public boolean isCurrency(int column) {
        throw unsupported();
    }

    @Override public int isNullable(int column) {
        throw unsupported();
    }

    @Override public boolean isSigned(int column) {
        throw unsupported();
    }

    @Override public int getColumnDisplaySize(int column) {
        throw unsupported();
    }

    @Override public String getColumnLabel(int column) {
        throw unsupported();
    }

    @Override public String getColumnName(int column) {
        throw unsupported();
    }

    @Override public String getSchemaName(int column) {
        throw unsupported();
    }

    @Override public int getPrecision(int column) {
        throw unsupported();
    }

    @Override public int getScale(int column) {
        throw unsupported();
    }

    @Override public String getTableName(int column) {
        throw unsupported();
    }

    @Override public String getCatalogName(int column) {
        throw unsupported();
    }

    @Override public int getColumnType(int column) {
        throw unsupported();
    }

    @Override public String getColumnTypeName(int column) {
        throw unsupported();
    }

    @Override public boolean isReadOnly(int column) {
        throw unsupported();
    }

    @Override public boolean isWritable(int column) {
        throw unsupported();
    }

    @Override public boolean isDefinitelyWritable(int column) {
        throw unsupported();
    }

    @Override public String getColumnClassName(int column) {
        throw unsupported();
    }

    @Override public <T> T unwrap(Class<T> iface) {
        throw unsupported();
    }

    @Override public boolean isWrapperFor(Class<?> iface) {
        throw unsupported();
    }
}
