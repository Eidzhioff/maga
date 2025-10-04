package ru.unecon.phones;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class PhonesTest {
    // Тесты прописаны для примеров из ТЗ
    @Test
    void testNumbers1() {
        assertEquals(
                List.of("79219243276", "1234568"),
                Phones.phoneNumbers(List.of(
                        "+7(921)9243276",
                        "123-45-68"
                ))
        );
    }

    @Test
    void testNumbers2() {
        assertEquals(
                List.of("79219243276", "1234567","1234568"),
                Phones.phoneNumbers(List.of(
                        "+7(921)9243276",
                        "1234567",
                        "123-4568"
                ))
        );
    }

    @Test
    void testFormats() {
        assertEquals(
                List.of("1234567","79219243276"),
                Phones.phoneNumbers(List.of(
                        "12-345-67",
                        "12345-67",
                        "123-45-67",
                        "+7 (921)9243276",
                        "89219243276"
                ))
        );
    }
}
