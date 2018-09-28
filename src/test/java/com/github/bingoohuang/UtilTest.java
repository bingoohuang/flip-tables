package com.github.bingoohuang;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class UtilTest {
    @Test
    public void test() {
        assertThat(Util.displayLength("火")).isEqualTo(2);
        assertThat(Util.displayLength("火）")).isEqualTo(4);
        assertThat(Util.displayLength("权限,true开启，fasle关闭")).isEqualTo(24);
        assertThat(Util.displayLength("┌")).isEqualTo(1);
        assertThat(Util.displayLength(" ┌─────┬─────┐ ")).isEqualTo(15);
    }
}
