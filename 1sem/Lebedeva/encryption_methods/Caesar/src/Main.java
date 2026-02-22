public class Main {
    // Шифрование текста
    public static String encrypt(String text, int key) {

        StringBuilder result = new StringBuilder();

        // Приводим строку в посимвольный массив
        for (char c : text.toLowerCase().toCharArray()) {
                // Проверка, что не пропуск и не символ
                if (Character.isAlphabetic(c)) {
                    // Сдвиг на key символов
                    char new_letter = (char) (c + key);
                    // Обработка случая ArrayIndexOutOfBounds
                    if (new_letter > 'z') {
                        new_letter = (char) (new_letter - 26);
                    }
                    result.append(new_letter);
                }
                else {
                    result.append(c);
                }
        }
        return result.toString();
    }

    // Дешифрования текста
    public static String decrypt(String text, int key) {
        return encrypt(text, -key);
    }

    public static void main(String[] args) {
        String original = "Java is hard!";
        int key = 3;

        String encrypted = encrypt(original, key);
        System.out.println("encrypt: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("decrypt: " + decrypted);
    }
}
