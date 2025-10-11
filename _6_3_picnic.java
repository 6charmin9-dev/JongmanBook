import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _6_3_picnic {
/*
# 입력 
첫번째 줄: 테스트 케이스 수: C (C <= 50) 
각 테스트 케이스의 첫 줄: 학생수: n (2 <= n <= 10). 학생들의 수는 짝수 | 친구 쌍의 수: m (0 <= m <= n(n-1)/2)
그 다음 줄: m개의 정수 쌍. 서로 친구인 두 학생의 번호. 번호는 모두 0부터 n-1 사이의 정수. 같은 쌍은 입력에 두번 주어지지 않음. 

# 출력
각 테스트 케이스 마다 한 줄에 모든 학생을 친구끼리만 짝지어 줄 수 있는 방법의 수 출력

# input
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

# output
1
3
4
*/
    public static int n;
    public static boolean[][] areFriends;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        while(N-- > 0) {
            String[] nm = br.readLine().split(" ");
            n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            areFriends = new boolean[n][n];
            String[] friends = br.readLine().split(" ");
            for(int i = 0; i < 2 * m; i += 2) {
                int a = Integer.parseInt(friends[i]);
                int b = Integer.parseInt(friends[i + 1]);
                areFriends[a][b] = areFriends[b][a] = true;
            }
            System.out.println(countPairings(new boolean[n]));
        }
    }

    /**
     * @description 모든 학생을 친구끼리만 짝지어 줄 수 있는 방법의 수를 반환
     * @param taken taken[i] = i번째 학생이 짝을 이미 이룬 경우 true, 아닌 경우 false
     * @return
     */
    public static int countPairings(boolean[] taken) {
        int firstFree = -1;
        for(int i = 0; i < n; i++) {
            if(!taken[i]) {
                firstFree = i;
                break;
            }
        }
        if(firstFree == -1) return 1; // 기저 사례: 모든 학생이 짝을 이룸

        int ret = 0;
        taken[firstFree] = true;
        for(int pairWith = firstFree + 1; pairWith < n; pairWith++) {
            if(!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[pairWith] = true;
                ret += countPairings(taken);
                taken[pairWith] = false;
            }
        }
        taken[firstFree] = false;
        return ret;
    }       
}

// 시간복잡도: O((n-1)!!) (n: 학생 수)
// n의 상한: 18