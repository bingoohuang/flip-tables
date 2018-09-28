package com.github.bingoohuang.util;

import lombok.Value;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


@Value
public final class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String nickName;
    private final LocalDate birthday;
    private final LocalTime workTime;
    private final DateTime joinTime;
}
