package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
# Input
테스트 케이스의 수 C(C<=50)
문자열의 수 n(1<=n<=100)
문자열의 길이 1000이하의 자연수 

3
3
2 2 4
5
3 1 3 4 1
8
1 1 1 1 1 1 1 2

# Output
12
26
27
 */
public class _10_4_strjoin {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        for (int i = 0; i < C; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int[] len = new int[n];
            for (int j = 0; j < n; j++) {
                len[j] = Integer.parseInt(str[j]);
            }
            System.out.println(concat(len));
        }
    }

    public static int concat(int[] len) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<len.length; i++) {
            pq.offer(len[i]);
        }

        int total = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            total += a + b;
            pq.offer(a + b);
        }
        return total;
    }
}
