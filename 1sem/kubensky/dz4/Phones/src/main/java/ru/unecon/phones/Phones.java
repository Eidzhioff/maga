package ru.unecon.phones;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Phones {

    private static final Pattern sevenDigits = Pattern.compile("^(\\d{3}(-\\d{2}){2})|(\\d{3}-\\d{4})|(\\d{7})$");
    private static final Pattern tenDigits = Pattern.compile("^\\+7\\(\\d{3}\\)\\d{7}$|^8\\d{10}$");

    private static String cleaningPhone(String phone) {
        return phone.replaceAll("\\D", "");
    }

    private static String reformTenDigits(String phone) {
        String digits = cleaningPhone(phone);
        return digits.startsWith("8") ? "7" + digits.substring(1) : digits;
    }

    private static boolean isSevenDigits(String phone) {
        return sevenDigits.matcher(phone).matches();
    }

    private static boolean isTenDigits(String phone) {
        return tenDigits.matcher(phone).matches();
    }

    public static List<String> phoneNumbers(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String phone : list) {
            if (isSevenDigits(phone)) {
                result.add(cleaningPhone(phone));
            } else if (isTenDigits(phone)) {
                result.add(reformTenDigits(phone));
            }
        }
        return result;
    }
}
