package com.jakewharton.fliptables;

import com.jakewharton.fliptables.util.FakeResultSet;
import com.jakewharton.fliptables.util.Person;
import com.jakewharton.fliptables.util.PersonType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        String table = FlipTableConverters.fromIterable(people, Person.class);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void emptyIterator() {
        List<Person> people = Collections.emptyList();
        String expected = "" +
                "┌─────┬──────────┬───────────┬──────────┬──────────┬──────────┬──────────┐\n" +
                "│ Age │ Birthday │ FirstName │ JoinTime │ LastName │ NickName │ WorkTime │\n" +
                "╞═════╧══════════╧═══════════╧══════════╧══════════╧══════════╧══════════╡\n" +
                "│ (empty)                                                                │\n" +
                "└────────────────────────────────────────────────────────────────────────┘\n";
        String table = FlipTableConverters.fromIterable(people, Person.class);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void simpleResultSet() throws SQLException {
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
        String table = FlipTableConverters.fromResultSet(resultSet);
        assertThat(table).isEqualTo(expected);
    }

    @Test public void emptyResultSet() throws SQLException {
        String[] headers = {"English", "Digit", "Spanish"};
        String[][] data = {};
        ResultSet resultSet = new FakeResultSet(headers, data);
        String expected = ""
                + "┌─────────┬───────┬─────────┐\n"
                + "│ English │ Digit │ Spanish │\n"
                + "╞═════════╧═══════╧═════════╡\n"
                + "│ (empty)                   │\n"
                + "└───────────────────────────┘\n";
        String table = FlipTableConverters.fromResultSet(resultSet);
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
        String table = FlipTableConverters.fromObjects(headers, data);
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
        String table = FlipTableConverters.fromObjects(headers, data);
        assertThat(table).isEqualTo(expected);
    }
}
