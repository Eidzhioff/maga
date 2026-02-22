package ru.unecon.person;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class PersonReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Person> readPersons(String resourceFileName) {
        try (InputStream inputStream = PersonReader.class.getClassLoader()
                .getResourceAsStream(resourceFileName)) {

            if (inputStream == null) {
                throw new InvalidPersonDataException("Файл не найден: " + resourceFileName);
            }

            return mapper.readValue(inputStream, new TypeReference<List<Person>>() {});

        } catch (Exception e) {
            if (e instanceof InvalidPersonDataException) {
                throw (InvalidPersonDataException) e;
            }
            throw new InvalidPersonDataException("Ошибка чтения файла: " + e.getMessage(), e);
        }
    }
}
