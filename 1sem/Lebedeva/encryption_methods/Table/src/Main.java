import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList get_alphabet(){
        String alphabet = "abcdefghiklmnopqrstuvwxyz";

        ArrayList<String> sub_alpha = new ArrayList<>(
                Arrays.asList(alphabet.split("(?<=\\G.{5})"))
        );

        ArrayList<ArrayList<String>> table = new ArrayList<>();

        for (String str : sub_alpha) {
            ArrayList<String> chars = new ArrayList<>();
            for (char c : str.toCharArray()) {
                chars.add(String.valueOf(c));
            }
            table.add(chars);
        }
        return table;
    }
    public static void main(String[] args) {


        String code = "0002 0302";

        StringBuilder result = new StringBuilder();

        String[][] alphabet = {
                {"a", "b", "c", "d", "e" },
                {"f", "g", "h", "i", "k" },
                {"l", "m", "n", "o", "p" },
                {"q", "r", "s", "t", "u" },
                {"v", "w", "x", "y", "z" }
        };

        System.out.println(alphabet);
    }
}
