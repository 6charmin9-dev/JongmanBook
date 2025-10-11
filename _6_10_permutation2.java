import java.util.ArrayList;
import java.util.List;

public class _6_10_permutation2 {
    public static void main(String[] args) {
        System.out.println(permute(4, 2)); // [[0, 1], [0, 2], [0, 3], [1, 0], [1, 2], [1, 3], [2, 0], [2, 1], [2, 3], [3, 0], [3, 1], [3, 2]]
    }

    public static List<List<Integer>> permute(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(new ArrayList<>(), n, k, result);
        return result;
    }

    private static void permuteHelper(List<Integer> pickedList, int n, int k, List<List<Integer>> result) {
        if (pickedList.size() == k) {
            result.add(new ArrayList<>(pickedList));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (pickedList.contains(i)) continue; // 중복된 원소를 피하기 위해
            pickedList.add(i);
            permuteHelper(pickedList, n, k, result);
            pickedList.remove(pickedList.size() - 1);
        }
    }
}
