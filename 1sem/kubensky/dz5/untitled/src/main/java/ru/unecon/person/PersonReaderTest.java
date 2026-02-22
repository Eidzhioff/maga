package ru.unecon.person;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


import java.util.List;

public class PersonReaderTest {

    @Test
    public void testReadValidPersons() {
        List<Person> persons = PersonReader.readPersons("person_test.json");

        assertEquals(1, persons.size());

        Person first = persons.get(0);
        assertEquals("Ivan", first.getFirstName());
        assertEquals("Ivanov", first.getSurName());
        assertEquals("9111234567", first.getPhone());
        assertEquals(22, first.getAge());
    }

//    Для проверки просто менял в JSON - файле данные на некорректные
    @Test
    public void testInvalidPhone() {
        assertThrows(InvalidPersonDataException.class, () ->
                PersonReader.readPersons("person_test.json"));
    }

    @Test
    public void testInvalidAge() {
        assertThrows(InvalidPersonDataException.class, () ->
                PersonReader.readPersons("person_test.json"));
    }

    @Test
    public void testEmptyName() {
        assertThrows(InvalidPersonDataException.class, () ->
                PersonReader.readPersons("person_test.json"));
    }

    @Test
    public void testFileNotFound() {
        assertThrows(InvalidPersonDataException.class, () ->
                PersonReader.readPersons("person_test.json"));
    }
}

