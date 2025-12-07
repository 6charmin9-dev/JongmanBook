package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
# Input
테스트 케이스의 수 C(C<=50)
초소의 개수 n(1<=n<=100)
초소의 위치 y_i, x_i, 감시 범위 r_i (0<=r_i=16.001)

성벽은 (0, 0)을 중심으로 하는 반지름 8인 원

3
10
7.02066050 -3.83540431 4.0
-7.23257714 -3.41903904 2.0
0.00000000 -8.00000000 8.0
-8.00000000 -0.00000000 4.8
-6.47213595 4.70228202 3.2
-4.70228202 6.47213595 4.8
7.60845213 -2.47213595 1.6
-2.47213595 -7.60845213 8.8
6.47213595 4.70228202 7.6
-0.00000000 8.00000000 4.8
4
8.00000000 0.00000000 8.00
0.00000000 -8.00000000 8.00
-8.00000000 -0.00000000 8.00
1.25147572 7.90150672 5.40
1
8 0 15.99

# Output
5
4
IMPOSSIBLE

 */
public class _10_6_minastirith {
    public static int INF = 987654321;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        for (int i=0; i<C; i++) {
            int n = Integer.parseInt(br.readLine());
            double[] y = new double[n];
            double[] x = new double[n];
            double[] r = new double[n];
            for (int j=0; j<n; j++) {
                String[] input = br.readLine().split(" ");
                y[j] = Double.parseDouble(input[0]);
                x[j] = Double.parseDouble(input[1]);
                r[j] = Double.parseDouble(input[2]);
            }
            Pair[] ranges = convertToRange(y, x, r);
            int result = solveCircular(ranges);
            System.out.println(result < INF ? result : "IMPOSSIBLE");
        }
    }

    public static int solveCircular(Pair[] ranges) {
        Arrays.sort(ranges);
        int count = INF;
        for (Pair range : ranges) {
            if (range.first <= 0 || range.second >= 2 * Math.PI) {
                double begin = (range.second) % (2 * Math.PI);
                double end = (range.first + Math.PI * 2) % (2 * Math.PI);
                count = Math.min(count, 1 + solveLinear(ranges, begin, end));
            }
        }
        return count;
    }

    public static int solveLinear(Pair[] ranges, double begin, double end) {
        int count = 0;
        int idx = 0;
        while (begin < end) {
            double maxCoverEnd = -1;
            while (idx < ranges.length && ranges[idx].first <= begin) {
                maxCoverEnd = Math.max(maxCoverEnd, ranges[idx].second);
                ++idx;
            }
            if (maxCoverEnd <= begin) {
                return INF;
            }
            begin = maxCoverEnd;
            count++;
        }
        return count;
    }

    public static Pair[] convertToRange(double[] y, double[] x, double[] r) {
        int n = y.length;
        Pair[] ranges = new Pair[n];
        for (int i = 0; i < n; i++) {
            double loc = (Math.atan2(y[i], x[i]) + 2 * Math.PI) % (2 * Math.PI);
            double range = 2 * Math.asin(r[i] / 16);
            ranges[i] = new Pair(loc - range, loc + range);
        }
        return ranges;
    }

    public static class Pair implements Comparable<Pair>{
        double first;
        double second;
        
        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(this.first, o.first);
        }
    }
}

