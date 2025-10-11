import java.util.ArrayList;
import java.util.List;

public class _6_10_combination {
    public static void main(String[] args) {
        System.out.println(combine(4, 2)); // [[0, 1], [0, 2], [0, 3], [1, 2], [1, 3], [2, 3]]
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 0, n, k);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> pickedList, int start, int n, int k) {
        if (pickedList.size() == k) {
            result.add(new ArrayList<>(pickedList));
            return;
        }
        for (int i = start; i < n; i++) {
            pickedList.add(i);
            backtrack(result, pickedList, i + 1, n, k);
            pickedList.remove(pickedList.size() - 1);
        }
    }
}

/*
🔍 백트래킹(backtracking)의 뜻

“모든 가능한 경우의 수를 탐색하되,
조건을 만족하지 않는 경로는 더 이상 탐색하지 않고 되돌아간다(backtrack)”는 탐색 기법

“현재까지 고른 조합(pickedList)”을 기준으로
다음 원소를 고르거나 되돌아가는 과정을 반복


 */