package com.github.bingoohuang;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.github.bingoohuang.util.FakeResultSet;
import com.github.bingoohuang.util.Person;
import com.github.bingoohuang.util.PersonType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;


public class FlipTableConvertersTest {
    static DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Test public void simpleIterator() {
        List<Person> people = Arrays.asList( //
                new Person("Big", "Bird", 16, "Big Yellow",
                        dtFormatter.parseLocalDate("2018-09-27 17:53:55"),
                        dtFormatter.parseLocalTime("2018-09-27 17:54:08"),
                        dtFormatter.parseDateTime("2018-09-27 17:54:20")), //
                new Person("Joe", "史密斯", 42, "Proposition Joe",
                        dtFormatter.parseLocalDate("2018-09-27 17:53:55"),
                        dtFormatter.parseLocalTime("2018-09-27 17:54:08"),
                        dtFormatter.parseDateTime("2018-09-27 17:54:20")), //
                new Person("Oscar", "Grouchant", 8, "Oscar The Grouch",
                        null,
                        dtFormatter.parseLocalTime("2018-09-27 17:54:08"),
                        dtFormatter.parseDateTime("2018-09-27 17:54:20")) //
        );
        String expected = "" +
                "┌─────┬────────────┬───────────┬─────────────────────┬───────────┬──────────────────┬──────────┐\n" +
                "│ Age │ Birthday   │ FirstName │ JoinTime            │ LastName  │ NickName         │ WorkTime │\n" +
                "╞═════╪════════════╪═══════════╪═════════════════════╪═══════════╪══════════════════╪══════════╡\n" +
                "│ 16  │ 2018-09-27 │ Big       │ 2018-09-27 17:54:20 │ Bird      │ Big Yellow       │ 17:54:08 │\n" +
                "├─────┼────────────┼───────────┼─────────────────────┼───────────┼──────────────────┼──────────┤\n" +
                "│ 42  │ 2018-09-27 │ Joe       │ 2018-09-27 17:54:20 │ 史密斯    │ Proposition Joe  │ 17:54:08 │\n" +
                "├─────┼────────────┼───────────┼─────────────────────┼───────────┼──────────────────┼──────────┤\n" +
                "│ 8   │ (null)     │ Oscar     │ 2018-09-27 17:54:20 │ Grouchant │ Oscar The Grouch │ 17:54:08 │\n" +
                "└─────┴────────────┴───────────┴─────────────────────┴───────────┴──────────────────┴──────────┘\n";

        String table = FlipTable.of(people);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void emptyIterator() {
        List<Person> people = Collections.emptyList();
        String expected = "" +
                "┌───────┐\n" +
                "│ Empty │\n" +
                "╘═══════╛\n";
        String table = FlipTable.of(people);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void simpleMap() {
        List<Map<String, String>> list = Lists.newArrayList();
        list.add(mapOf("English", "One", "Digit", "1", "Spanish", "Uno"));
        list.add(mapOf("English", "Two", "Digit", "2", "Spanish", "Dos"));
        list.add(mapOf("English", "Three", "Digit", "3", "Spanish", "Tres"));

        String expected = "" +
                "┌───────┬─────────┬─────────┐\n" +
                "│ Digit │ English │ Spanish │\n" +
                "╞═══════╪═════════╪═════════╡\n" +
                "│ 1     │ One     │ Uno     │\n" +
                "├───────┼─────────┼─────────┤\n" +
                "│ 2     │ Two     │ Dos     │\n" +
                "├───────┼─────────┼─────────┤\n" +
                "│ 3     │ Three   │ Tres    │\n" +
                "└───────┴─────────┴─────────┘\n";
        String table = FlipTable.of(list);
        assertThat(table).isEqualTo(expected);
    }

    private Map<String, String> mapOf(String... kvs) {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 0; i + 1 < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }

        return map;
    }

    @Test public void simpleResultSet() {
        String[] headers = {"English", "Digit", "Spanish"};
        String[][] data = {
                {"One", "1", "Uno"},
                {"Two", "2", "Dos"},
                {"Three", "3", "Tres"},
        };
        ResultSet resultSet = new FakeResultSet(headers, data);
        String expected = ""
                + "┌─────────┬───────┬─────────┐\n"
                + "│ English │ Digit │ Spanish │\n"
                + "╞═════════╪═══════╪═════════╡\n"
                + "│ One     │ 1     │ Uno     │\n"
                + "├─────────┼───────┼─────────┤\n"
                + "│ Two     │ 2     │ Dos     │\n"
                + "├─────────┼───────┼─────────┤\n"
                + "│ Three   │ 3     │ Tres    │\n"
                + "└─────────┴───────┴─────────┘\n";
        String table = FlipTable.of(resultSet);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void emptyResultSet() {
        String[] headers = {"English", "Digit", "Spanish"};
        String[][] data = {};
        ResultSet resultSet = new FakeResultSet(headers, data);
        String expected = ""
                + "┌─────────┬───────┬─────────┐\n"
                + "│ English │ Digit │ Spanish │\n"
                + "╞═════════╧═══════╧═════════╡\n"
                + "│ (empty)                   │\n"
                + "└───────────────────────────┘\n";
        String table = FlipTable.of(resultSet);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void simpleObjects() {
        String[] headers = {"First Name", "Last Name", "Age", "Type"};
        Object[][] data = { //
                {"Big", "Bird", 16, PersonType.COSTUME}, //
                {"Joe", "Smith", 42, PersonType.HUMAN}, //
                {"Oscar", "Grouchant", 8, PersonType.PUPPET} //
        };
        String expected = ""
                + "┌────────────┬───────────┬─────┬─────────┐\n"
                + "│ First Name │ Last Name │ Age │ Type    │\n"
                + "╞════════════╪═══════════╪═════╪═════════╡\n"
                + "│ Big        │ Bird      │ 16  │ COSTUME │\n"
                + "├────────────┼───────────┼─────┼─────────┤\n"
                + "│ Joe        │ Smith     │ 42  │ HUMAN   │\n"
                + "├────────────┼───────────┼─────┼─────────┤\n"
                + "│ Oscar      │ Grouchant │ 8   │ PUPPET  │\n"
                + "└────────────┴───────────┴─────┴─────────┘\n";
        String table = FlipTable.of(headers, data);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void emptyObjects() {
        String[] headers = {"First Name", "Last Name", "Age", "Type"};
        Object[][] data = {};
        String expected = ""
                + "┌────────────┬───────────┬─────┬──────┐\n"
                + "│ First Name │ Last Name │ Age │ Type │\n"
                + "╞════════════╧═══════════╧═════╧══════╡\n"
                + "│ (empty)                             │\n"
                + "└─────────────────────────────────────┘\n";
        String table = FlipTable.of(headers, data);
        assertThat(table).isEqualTo(expected);
    }


    @Test public void singleValueList() {
        List<Integer> list = Lists.newArrayList();
        list.add(100);
        list.add(200);
        list.add(300);

        String expected = "" +
                "┌─────────┐\n" +
                "│ Value   │\n" +
                "╞═════════╡\n" +
                "│ 100     │\n" +
                "├─────────┤\n" +
                "│ 200     │\n" +
                "├─────────┤\n" +
                "│ 300     │\n" +
                "└─────────┘\n";
        String table = FlipTable.of(list);
        assertThat(table).isEqualTo(expected);
    }


    @Test public void allNulls() {
        List<Integer> list = Lists.newArrayList();
        list.add(null);
        list.add(null);

        String expected = "" +
                "┌─────────┐\n" +
                "│ Values  │\n" +
                "╞═════════╡\n" +
                "│ (null)  │\n" +
                "├─────────┤\n" +
                "│ (null)  │\n" +
                "└─────────┘\n" ;
        String table = FlipTable.of(list);
        assertThat(table).isEqualTo(expected);
    }
}
