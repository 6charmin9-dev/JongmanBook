package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;

/*
# Input
테스트 케이스 수 C(C<=300)
도시락의 수 n(1<=n<=10000)
도시락을 데우는 데 걸리는 시간 m_i 
도시락을 데우는 데 걸리는 시간 e_i

2
3
2 2 2
2 2 2
3
1 2 3
1 2 1

# Output
첫 번째 도시락을 데우기 시작할 때부터 모든 사람이 도시락을 다 먹을 때까지 걸리느 시간 

8
7
 */
public class _10_2_lunchbox {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        for (int i=0; i<C; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] m = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] e = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(heat(m, e));
        }
    }

    public static int heat(int[] m, int[] e) {
        // 도시락을 먹는데 걸리는 시간 역순으로 정렬
        Queue<LunchBox> pq = new PriorityQueue<>();
        for (int i = 0; i < m.length; i++) {
            pq.offer(new LunchBox(m[i], -e[i]));
        }
        // 도시락을 데우는 데 걸리는 시간 계산
        int total = 0; int beginEat = 0;
        while (!pq.isEmpty()) {
            LunchBox lb = pq.poll();
            beginEat += lb.m;
            total = Math.max(total, beginEat + -lb.e);
        }
        return total;
    }

    static class LunchBox implements Comparable<LunchBox> {
        int m;
        int e;

        LunchBox(int m, int e) {
            this.m = m;
            this.e = e;
        }

        @Override
        public int compareTo(LunchBox o) {
            return Integer.compare(this.e, o.e);
        }
    }
}
