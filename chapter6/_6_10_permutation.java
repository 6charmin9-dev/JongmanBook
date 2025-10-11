import java.util.ArrayList;
import java.util.List;

public class _6_10_permutation {
    public static void main(String[] args) {
        System.out.println(permute("abc")); // [abc, acb, bac, bca, cab, cba]
    }

    public static List<String> permute(String str) {
        List<String> result = new ArrayList<>();
        permuteHelper("", str, result);
        return result;
    }

    private static void permuteHelper(String prefix, String remaining, List<String> result) {
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            permuteHelper(newPrefix, newRemaining, result);
        }
    }
}