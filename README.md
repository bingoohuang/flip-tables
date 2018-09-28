Flip Tables
===========

[![Build Status](https://travis-ci.org/bingoohuang/fliptables.svg?branch=master)](https://travis-ci.org/bingoohuang/fliptables)
[![Coverage Status](https://coveralls.io/repos/github/bingoohuang/fliptables/badge.svg?branch=master)](https://coveralls.io/github/bingoohuang/fliptables?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bingoohuang/fliptables/badge.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/com.github.bingoohuang/fliptables/)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Because pretty-printing text tables in Java should be easy.

(╯°□°）╯︵ ┻━┻



Usage
-----

A `FlipTable` requires headers and data in string form:
```java
String[] headers = { "Test", "Header" };
String[][] data = {
    { "Foo", "Bar" },
    { "Kit", "Kat" },
};
System.out.println(FlipTable.of(headers, data));
```
```
┌──────┬────────┐
│ Test │ Header │
╞══════╪════════╡
│ Foo  │ Bar    │
├──────┼────────┤
│ Kit  │ Kat    │
└──────┴────────┘
```

They can be empty:
```java
String[] headers = { "Test", "Header" };
String[][] data = {};
System.out.println(FlipTable.of(headers, data));
```
```
┌──────┬────────┐
│ Test │ Header │
╞══════╧════════╡
│ (empty)       │
└───────────────┘
```

Newlines are supported:
```java
String[] headers = { "One Two\nThree", "Four" };
String[][] data = { { "Five", "Six\nSeven Eight" } };
System.out.println(FlipTable.of(headers, data));
```
```
┌─────────┬─────────────┐
│ One Two │ Four        │
│ Three   │             │
╞═════════╪═════════════╡
│ Five    │ Six         │
│         │ Seven Eight │
└─────────┴─────────────┘
```

Which means tables can be nested:
```java
String[] innerHeaders = { "One", "Two" };
String[][] innerData = { { "1", "2" } };
String inner = FlipTable.of(innerHeaders, innerData);
String[] headers = { "Left", "Right" };
String[][] data = { { inner, inner } };
System.out.println(FlipTable.of(headers, data));
```
```
┌───────────────┬───────────────┐
│ Left          │ Right         │
╞═══════════════╪═══════════════╡
│ ┌─────┬─────┐ │ ┌─────┬─────┐ │
│ │ One │ Two │ │ │ One │ Two │ │
│ ╞═════╪═════╡ │ ╞═════╪═════╡ │
│ │ 1   │ 2   │ │ │ 1   │ 2   │ │
│ └─────┴─────┘ │ └─────┴─────┘ │
└───────────────┴───────────────┘
```

Helper methods convert from types like lists:
```java
List<Person> people = Arrays.asList(new Person("Foo", "Bar"), new Person("Kit", "Kat"));
System.out.println(FlipTable.of(people));
```
```
┌───────────┬──────────┐
│ FirstName │ LastName │
╞═══════════╪══════════╡
│ Foo       │ Bar      │
├───────────┼──────────┤
│ Kit       │ Kat      │
└───────────┴──────────┘
```

Or a map list:
```java
List<Map<String, String>> list = Lists.newArrayList();
list.add(mapOf("English", "One", "Digit", "1", "Spanish", "Uno"));
list.add(mapOf("English", "Two", "Digit", "2", "Spanish", "Dos"));
list.add(mapOf("English", "Three", "Digit", "3", "Spanish", "Tres"));
System.out.println(FlipTable.of(list));
```
```
┌───────┬─────────┬─────────┐
│ Digit │ English │ Spanish │
╞═══════╪═════════╪═════════╡
│ 1     │ One     │ Uno     │
├───────┼─────────┼─────────┤
│ 2     │ Two     │ Dos     │
├───────┼─────────┼─────────┤
│ 3     │ Three   │ Tres    │
└───────┴─────────┴─────────┘
```

Or a database result:
```java
ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name FROM users");
System.out.println(FlipTable.of(resultSet));
```
```
┌────────────┬───────────┐
│ first_name │ last_name │
╞════════════╪═══════════╡
│ Jake       │ Wharton   │
├────────────┼───────────┤
│ Edward     │ Snowden   │
└────────────┴───────────┘
```


Arbitrary objects are also supported:
```java
String[] headers = { "First Name", "Last Name", "Age", "Type" };
Object[][] data = {
    { "Big", "Bird", 16, PersonType.COSTUME },
    { "乔伊", "史密斯", 42, PersonType.HUMAN },
    { "Oscar", "Grouchant", 8, PersonType.PUPPET }
};
System.out.println(FlipTable.of(headers, data));
```
```
┌────────────┬───────────┬─────┬─────────┐
│ First Name │ Last Name │ Age │ Type    │
╞════════════╪═══════════╪═════╪═════════╡
│ Big        │ Bird      │ 16  │ COSTUME │
├────────────┼───────────┼─────┼─────────┤
│ 乔伊       │ 史密斯    │ 42  │ HUMAN   │
├────────────┼───────────┼─────┼─────────┤
│ Oscar      │ Grouchant │ 8   │ PUPPET  │
└────────────┴───────────┴─────┴─────────┘
```
