package com.github.bingoohuang;

import com.github.bingoohuang.util.PersonType;
import lombok.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ReadmeTest {
    @Test
    public void test1() {
        String[] headers = { "Test", "Header" };
        String[][] data = {
                { "Foo", "Bar" },
                { "Kit", "Kat" },
        };
        System.out.println(FlipTable.of(headers, data));
    }

    @Test
    public void test2() {
        String[] headers = { "Test", "Header" };
        String[][] data = {};
        System.out.println(FlipTable.of(headers, data));
    }

    @Test
    public void test3() {
        String[] headers = { "One Two\nThree", "Four" };
        String[][] data = { { "Five", "Six\nSeven Eight" } };
        System.out.println(FlipTable.of(headers, data));
    }

    @Test
    public void test4() {
        String[] innerHeaders = { "One", "Two" };
        String[][] innerData = { { "1", "2" } };
        String inner = FlipTable.of(innerHeaders, innerData);
        String[] headers = { "Left", "Right" };
        String[][] data = { { inner, inner } };
        System.out.println(FlipTable.of(headers, data));
    }

    @Value
    public static class Person {
        private final String firstName;
        private final String lastName;
    }

    @Test
    public void test5() {
        List<Person> people = Arrays.asList(new Person("Foo", "Bar"), new Person("Kit", "Kat"));
        System.out.println(FlipTable.of(people));
    }

    @Test
    public void test6() {
        String[] headers = { "First Name", "Last Name", "Age", "Type" };
        Object[][] data = {
                { "Big", "Bird", 16, PersonType.COSTUME },
                { "乔伊", "史密斯", 42, PersonType.HUMAN },
                { "Oscar", "Grouchant", 8, PersonType.PUPPET }
        };
        System.out.println(FlipTable.of(headers, data));
    }
}
