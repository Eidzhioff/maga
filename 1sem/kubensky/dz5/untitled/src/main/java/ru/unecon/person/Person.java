package ru.unecon.person;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@ToString
public class Person {
    private final String firstName;
    private final String surName;
    private final String phone;
    private final byte age;

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("\\s*(\\+7|8)\\(\\d{3}\\)\\d{3}-?\\d{2}-?\\d{2}");

    @JsonCreator
    public Person(
            @JsonProperty(value = "first-name", required = true) String firstName,
            @JsonProperty(value = "family-name", required = true) String surName,
            @JsonProperty(value = "phone", required = true) String phone,
            @JsonProperty(value = "age", required = true) int age) {

        if (firstName == null || firstName.isBlank()) {
            throw new InvalidPersonDataException("Имя не может быть пустым");
        }
        if (surName == null || surName.isBlank()) {
            throw new InvalidPersonDataException("Фамилия не может быть пустой");
        }

        this.firstName = firstName;
        this.surName = surName;

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPersonDataException("Неправильный формат телефона: " + phone);
        }
        this.phone = phone.replaceAll("\\D+", "").substring(1);

        if (age < 0 || age > 120) {
            throw new InvalidPersonDataException("Возраст должен быть в интервале от 0 до 120: " + age);
        }
        this.age = (byte) age;
    }

    public String getFirstName() { return firstName; }
    public String getSurName() { return surName; }
    public String getPhone() { return phone; }
    public byte getAge() { return age; }

    @Override
    public String toString() {
        return String.format("Person{firstName='%s', surName='%s', phone='%s', age=%d}",
                firstName, surName, phone, age);
    }
}

