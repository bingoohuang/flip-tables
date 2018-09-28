package com.github.bingoohuang.util;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public abstract class AbstractResultSet implements ResultSet {
    @Override public boolean next() {
        throw unsupported();
    }

    @Override public void close() {
        throw unsupported();
    }

    @Override public boolean wasNull() {
        throw unsupported();
    }

    @Override public String getString(int columnIndex) {
        throw unsupported();
    }

    @Override public boolean getBoolean(int columnIndex) {
        throw unsupported();
    }

    @Override public byte getByte(int columnIndex) {
        throw unsupported();
    }

    @Override public short getShort(int columnIndex) {
        throw unsupported();
    }

    @Override public int getInt(int columnIndex) {
        throw unsupported();
    }

    @Override public long getLong(int columnIndex) {
        throw unsupported();
    }

    @Override public float getFloat(int columnIndex) {
        throw unsupported();
    }

    @Override public double getDouble(int columnIndex) {
        throw unsupported();
    }

    @Override public BigDecimal getBigDecimal(int columnIndex, int scale) {
        throw unsupported();
    }

    @Override public byte[] getBytes(int columnIndex) {
        throw unsupported();
    }

    @Override public Date getDate(int columnIndex) {
        throw unsupported();
    }

    @Override public Time getTime(int columnIndex) {
        throw unsupported();
    }

    @Override public Timestamp getTimestamp(int columnIndex) {
        throw unsupported();
    }

    @Override public InputStream getAsciiStream(int columnIndex) {
        throw unsupported();
    }

    @Override public InputStream getUnicodeStream(int columnIndex) {
        throw unsupported();
    }

    @Override public InputStream getBinaryStream(int columnIndex) {
        throw unsupported();
    }

    @Override public String getString(String columnLabel) {
        throw unsupported();
    }

    @Override public boolean getBoolean(String columnLabel) {
        throw unsupported();
    }

    @Override public byte getByte(String columnLabel) {
        throw unsupported();
    }

    @Override public short getShort(String columnLabel) {
        throw unsupported();
    }

    @Override public int getInt(String columnLabel) {
        throw unsupported();
    }

    @Override public long getLong(String columnLabel) {
        throw unsupported();
    }

    @Override public float getFloat(String columnLabel) {
        throw unsupported();
    }

    @Override public double getDouble(String columnLabel) {
        throw unsupported();
    }

    @Override public BigDecimal getBigDecimal(String columnLabel, int scale) {
        throw unsupported();
    }

    @Override public byte[] getBytes(String columnLabel) {
        throw unsupported();
    }

    @Override public Date getDate(String columnLabel) {
        throw unsupported();
    }

    @Override public Time getTime(String columnLabel) {
        throw unsupported();
    }

    @Override public Timestamp getTimestamp(String columnLabel) {
        throw unsupported();
    }

    @Override public InputStream getAsciiStream(String columnLabel) {
        throw unsupported();
    }

    @Override public InputStream getUnicodeStream(String columnLabel) {
        throw unsupported();
    }

    @Override public InputStream getBinaryStream(String columnLabel) {
        throw unsupported();
    }

    @Override public SQLWarning getWarnings() {
        throw unsupported();
    }

    @Override public void clearWarnings() {
        throw unsupported();
    }

    @Override public String getCursorName() {
        throw unsupported();
    }

    @Override public ResultSetMetaData getMetaData() {
        throw unsupported();
    }

    @Override public Object getObject(int columnIndex) {
        throw unsupported();
    }

    @Override public Object getObject(String columnLabel) {
        throw unsupported();
    }

    @Override public int findColumn(String columnLabel) {
        throw unsupported();
    }

    @Override public Reader getCharacterStream(int columnIndex) {
        throw unsupported();
    }

    @Override public Reader getCharacterStream(String columnLabel) {
        throw unsupported();
    }

    @Override public BigDecimal getBigDecimal(int columnIndex) {
        throw unsupported();
    }

    @Override public BigDecimal getBigDecimal(String columnLabel) {
        throw unsupported();
    }

    @Override public boolean isBeforeFirst() {
        throw unsupported();
    }

    @Override public boolean isAfterLast() {
        throw unsupported();
    }

    @Override public boolean isFirst() {
        throw unsupported();
    }

    @Override public boolean isLast() {
        throw unsupported();
    }

    @Override public void beforeFirst() {
        throw unsupported();
    }

    @Override public void afterLast() {
        throw unsupported();
    }

    @Override public boolean first() {
        throw unsupported();
    }

    @Override public boolean last() {
        throw unsupported();
    }

    @Override public int getRow() {
        throw unsupported();
    }

    @Override public boolean absolute(int row) {
        throw unsupported();
    }

    @Override public boolean relative(int rows) {
        throw unsupported();
    }

    @Override public boolean previous() {
        throw unsupported();
    }

    @Override public void setFetchDirection(int direction) {
        throw unsupported();
    }

    @Override public int getFetchDirection() {
        throw unsupported();
    }

    @Override public void setFetchSize(int rows) {
        throw unsupported();
    }

    @Override public int getFetchSize() {
        throw unsupported();
    }

    @Override public int getType() {
        throw unsupported();
    }

    @Override public int getConcurrency() {
        throw unsupported();
    }

    @Override public boolean rowUpdated() {
        throw unsupported();
    }

    @Override public boolean rowInserted() {
        throw unsupported();
    }

    @Override public boolean rowDeleted() {
        throw unsupported();
    }

    @Override public void updateNull(int columnIndex) {
        throw unsupported();
    }

    @Override public void updateBoolean(int columnIndex, boolean x) {
        throw unsupported();
    }

    @Override public void updateByte(int columnIndex, byte x) {
        throw unsupported();
    }

    @Override public void updateShort(int columnIndex, short x) {
        throw unsupported();
    }

    @Override public void updateInt(int columnIndex, int x) {
        throw unsupported();
    }

    @Override public void updateLong(int columnIndex, long x) {
        throw unsupported();
    }

    @Override public void updateFloat(int columnIndex, float x) {
        throw unsupported();
    }

    @Override public void updateDouble(int columnIndex, double x) {
        throw unsupported();
    }

    @Override public void updateBigDecimal(int columnIndex, BigDecimal x) {
        throw unsupported();
    }

    @Override public void updateString(int columnIndex, String x) {
        throw unsupported();
    }

    @Override public void updateBytes(int columnIndex, byte[] x) {
        throw unsupported();
    }

    @Override public void updateDate(int columnIndex, Date x) {
        throw unsupported();
    }

    @Override public void updateTime(int columnIndex, Time x) {
        throw unsupported();
    }

    @Override public void updateTimestamp(int columnIndex, Timestamp x) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(int columnIndex, InputStream x, int length) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(int columnIndex, InputStream x, int length) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(int columnIndex, Reader x, int length) {
        throw unsupported();
    }

    @Override public void updateObject(int columnIndex, Object x, int scaleOrLength) {
        throw unsupported();
    }

    @Override public void updateObject(int columnIndex, Object x) {
        throw unsupported();
    }

    @Override public void updateNull(String columnLabel) {
        throw unsupported();
    }

    @Override public void updateBoolean(String columnLabel, boolean x) {
        throw unsupported();
    }

    @Override public void updateByte(String columnLabel, byte x) {
        throw unsupported();
    }

    @Override public void updateShort(String columnLabel, short x) {
        throw unsupported();
    }

    @Override public void updateInt(String columnLabel, int x) {
        throw unsupported();
    }

    @Override public void updateLong(String columnLabel, long x) {
        throw unsupported();
    }

    @Override public void updateFloat(String columnLabel, float x) {
        throw unsupported();
    }

    @Override public void updateDouble(String columnLabel, double x) {
        throw unsupported();
    }

    @Override public void updateBigDecimal(String columnLabel, BigDecimal x) {
        throw unsupported();
    }

    @Override public void updateString(String columnLabel, String x) {
        throw unsupported();
    }

    @Override public void updateBytes(String columnLabel, byte[] x) {
        throw unsupported();
    }

    @Override public void updateDate(String columnLabel, Date x) {
        throw unsupported();
    }

    @Override public void updateTime(String columnLabel, Time x) {
        throw unsupported();
    }

    @Override public void updateTimestamp(String columnLabel, Timestamp x) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(String columnLabel, InputStream x, int length) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(String columnLabel, InputStream x, int length) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(String columnLabel, Reader reader, int length) {
        throw unsupported();
    }

    @Override public void updateObject(String columnLabel, Object x, int scaleOrLength) {
        throw unsupported();
    }

    @Override public void updateObject(String columnLabel, Object x) {
        throw unsupported();
    }

    @Override public void insertRow() {
        throw unsupported();
    }

    @Override public void updateRow() {
        throw unsupported();
    }

    @Override public void deleteRow() {
        throw unsupported();
    }

    @Override public void refreshRow() {
        throw unsupported();
    }

    @Override public void cancelRowUpdates() {
        throw unsupported();
    }

    @Override public void moveToInsertRow() {
        throw unsupported();
    }

    @Override public void moveToCurrentRow() {
        throw unsupported();
    }

    @Override public Statement getStatement() {
        throw unsupported();
    }

    @Override public Object getObject(int columnIndex, Map<String, Class<?>> map) {
        throw unsupported();
    }

    @Override public Ref getRef(int columnIndex) {
        throw unsupported();
    }

    @Override public Blob getBlob(int columnIndex) {
        throw unsupported();
    }

    @Override public Clob getClob(int columnIndex) {
        throw unsupported();
    }

    @Override public Array getArray(int columnIndex) {
        throw unsupported();
    }

    @Override public Object getObject(String columnLabel, Map<String, Class<?>> map) {
        throw unsupported();
    }

    @Override public Ref getRef(String columnLabel) {
        throw unsupported();
    }

    @Override public Blob getBlob(String columnLabel) {
        throw unsupported();
    }

    @Override public Clob getClob(String columnLabel) {
        throw unsupported();
    }

    @Override public Array getArray(String columnLabel) {
        throw unsupported();
    }

    @Override public Date getDate(int columnIndex, Calendar cal) {
        throw unsupported();
    }

    @Override public Date getDate(String columnLabel, Calendar cal) {
        throw unsupported();
    }

    @Override public Time getTime(int columnIndex, Calendar cal) {
        throw unsupported();
    }

    @Override public Time getTime(String columnLabel, Calendar cal) {
        throw unsupported();
    }

    @Override public Timestamp getTimestamp(int columnIndex, Calendar cal) {
        throw unsupported();
    }

    @Override public Timestamp getTimestamp(String columnLabel, Calendar cal) {
        throw unsupported();
    }

    @Override public URL getURL(int columnIndex) {
        throw unsupported();
    }

    @Override public URL getURL(String columnLabel) {
        throw unsupported();
    }

    @Override public void updateRef(int columnIndex, Ref x) {
        throw unsupported();
    }

    @Override public void updateRef(String columnLabel, Ref x) {
        throw unsupported();
    }

    @Override public void updateBlob(int columnIndex, Blob x) {
        throw unsupported();
    }

    @Override public void updateBlob(String columnLabel, Blob x) {
        throw unsupported();
    }

    @Override public void updateClob(int columnIndex, Clob x) {
        throw unsupported();
    }

    @Override public void updateClob(String columnLabel, Clob x) {
        throw unsupported();
    }

    @Override public void updateArray(int columnIndex, Array x) {
        throw unsupported();
    }

    @Override public void updateArray(String columnLabel, Array x) {
        throw unsupported();
    }

    @Override public RowId getRowId(int columnIndex) {
        throw unsupported();
    }

    @Override public RowId getRowId(String columnLabel) {
        throw unsupported();
    }

    @Override public void updateRowId(int columnIndex, RowId x) {
        throw unsupported();
    }

    @Override public void updateRowId(String columnLabel, RowId x) {
        throw unsupported();
    }

    @Override public int getHoldability() {
        throw unsupported();
    }

    @Override public boolean isClosed() {
        throw unsupported();
    }

    @Override public void updateNString(int columnIndex, String nString) {
        throw unsupported();
    }

    @Override public void updateNString(String columnLabel, String nString) {
        throw unsupported();
    }

    @Override public void updateNClob(int columnIndex, NClob nClob) {
        throw unsupported();
    }

    @Override public void updateNClob(String columnLabel, NClob nClob) {
        throw unsupported();
    }

    @Override public NClob getNClob(int columnIndex) {
        throw unsupported();
    }

    @Override public NClob getNClob(String columnLabel) {
        throw unsupported();
    }

    @Override public SQLXML getSQLXML(int columnIndex) {
        throw unsupported();
    }

    @Override public SQLXML getSQLXML(String columnLabel) {
        throw unsupported();
    }

    @Override public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
        throw unsupported();
    }

    @Override public void updateSQLXML(String columnLabel, SQLXML xmlObject) {
        throw unsupported();
    }

    @Override public String getNString(int columnIndex) {
        throw unsupported();
    }

    @Override public String getNString(String columnLabel) {
        throw unsupported();
    }

    @Override public Reader getNCharacterStream(int columnIndex) {
        throw unsupported();
    }

    @Override public Reader getNCharacterStream(String columnLabel) {
        throw unsupported();
    }

    @Override public void updateNCharacterStream(int columnIndex, Reader x, long length) {
        throw unsupported();
    }

    @Override public void updateNCharacterStream(String columnLabel, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(int columnIndex, InputStream x, long length) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(int columnIndex, InputStream x, long length) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(int columnIndex, Reader x, long length) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(String columnLabel, InputStream x, long length) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(String columnLabel, InputStream x, long length) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(String columnLabel, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateBlob(int columnIndex, InputStream inputStream, long length) {
        throw unsupported();
    }

    @Override public void updateBlob(String columnLabel, InputStream inputStream, long length) {
        throw unsupported();
    }

    @Override public void updateClob(int columnIndex, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateClob(String columnLabel, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateNClob(int columnIndex, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateNClob(String columnLabel, Reader reader, long length) {
        throw unsupported();
    }

    @Override public void updateNCharacterStream(int columnIndex, Reader x) {
        throw unsupported();
    }

    @Override public void updateNCharacterStream(String columnLabel, Reader reader) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(int columnIndex, InputStream x) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(int columnIndex, InputStream x) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(int columnIndex, Reader x) {
        throw unsupported();
    }

    @Override public void updateAsciiStream(String columnLabel, InputStream x) {
        throw unsupported();
    }

    @Override public void updateBinaryStream(String columnLabel, InputStream x) {
        throw unsupported();
    }

    @Override public void updateCharacterStream(String columnLabel, Reader reader) {
        throw unsupported();
    }

    @Override public void updateBlob(int columnIndex, InputStream inputStream) {
        throw unsupported();
    }

    @Override public void updateBlob(String columnLabel, InputStream inputStream) {
        throw unsupported();
    }

    @Override public void updateClob(int columnIndex, Reader reader) {
        throw unsupported();
    }

    @Override public void updateClob(String columnLabel, Reader reader) {
        throw unsupported();
    }

    @Override public void updateNClob(int columnIndex, Reader reader) {
        throw unsupported();
    }

    @Override public void updateNClob(String columnLabel, Reader reader) {
        throw unsupported();
    }

    @Override public <T> T getObject(int columnIndex, Class<T> type) {
        throw unsupported();
    }

    @Override public <T> T getObject(String columnLabel, Class<T> type) {
        throw unsupported();
    }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException();
    }

    @Override public <T> T unwrap(Class<T> iface) {
        throw unsupported();
    }

    @Override public boolean isWrapperFor(Class<?> iface) {
        throw unsupported();
    }
}
