import java.io.BufferedReader;

public class _6_8_clocksync {
/*
4*4 격자 형태로 배치된 열여섯개의 시계가 있다.
이 시계들은 모두 12시, 3시, 6시, 9시 중 하나를 가리키고 있다.
이 시계들이 모두 12시를 가리키도록 만들려고 한다.
시계의 시간을 조작하는 방법은 열개의 스위치를 조작하는 것으로, 각 스위치들이 모두 적게는 세 개에서 많게는 다섯 개의 시계에 연결되어 있다.
한 스위치를 누를 때마다 그 스위치와 연결된 시계들의 시간이 3시간씩 앞으로 간다.
스위치들과 그들이 연결된 시계들의 목록은 다음과 같다.

스위치 번호    연결된 시계들
0             0, 1, 2
1             3, 7, 9, 11
2             4, 10, 14, 15
3             0, 4, 5, 6, 7
4             6, 7, 8, 10, 12
5             0, 2, 14, 15
6             3, 14, 15
7             4, 5, 7, 14, 15
8             1, 2, 3, 4, 5
9             3, 4, 5, 9, 13

시계 번호는 0부터 15까지 매겨져 있다.
시계들이 현재 가리키는 시간들이 주어져 있을 때, 모든 시계가 12시를 가리키도록 만들기 위해 스위치를 최소 몇 번 눌러야 하는지를 구하여라. 
(10초 안에 실행, 64MB 메모리 제한)

# 입력
첫 줄에 테스트 케이스 개수 C(C <= 30)
각 테스트 케이스는 한줄에 16개의 정수로 주어지며, 각 정수는 0부터 15번까지 각 시계가 가리키는 시간을 12, 3, 6, 9 중 하나로 나타낸다.

# 출력
각 테스트 케이스 당 정수를 한 줄에 하나씩 출력한다. 이 정수는 시계를 모두 12시로 돌려놓기 위해 스위치를 눌러야 할 최소 횟수이며, 불가능할 경우 -1을 출력한다.

# input
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6

# output
2
9
*/
    public static int[][] switches = {
        {0, 1, 2},
        {3, 7, 9, 11},
        {4, 10, 14, 15},
        {0, 4, 5, 6, 7},
        {6, 7, 8, 10, 12},
        {0, 2, 14, 15},
        {3, 14, 15},
        {4, 5, 7, 14, 15},
        {1, 2, 3, 4, 5},
        {3, 4, 5, 9, 13}
    };

    public static int INF = 1048577;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while(C-- > 0) {
            String[] line = br.readLine().split(" ");
            int[] clocks = new int[16];
            for(int i = 0; i < 16; i++) {
                clocks[i] = Integer.parseInt(line[i]);
            }
            int ans = solve(clocks, 0);
            if(ans >= INF) ans = -1;
            System.out.println(ans);
        }
    }

    /**
     * @description clocks의 상태를 switchNum번째 스위치부터 눌러서 모두 12시로 맞추기 위해 눌러야 하는 최소 횟수를 반환
     * @param clocks 각 시계가 가리키는 시간을 담은 배열
     * @param switchNum 현재 살펴보고 있는 스위치 번호
     * @return
     */
    public static int solve(int[] clocks, int switchNum) {
        if(switchNum == 10) {
            if(areAligned(clocks)) return 0; // 리턴값은 스위치를 더 누를 필요가 없으므로 0
            else return INF;
        }
        int ret = INF;
        for(int cnt = 0; cnt < 4; cnt++) {
            ret = Math.min(ret, cnt + solve(clocks, switchNum + 1));
            push(clocks, switchNum);
        }
        return ret;
    }
    //시간복잡도: O(4^S) (S: 스위치 수)

    public static boolean areAligned(int[] clocks) {
        for(int time: clocks) {
            if(time != 12) return false;
        }
        return true;
    }

    /**
     * @description switchNum번째 스위치를 누른다.
     * @param clocks
     * @param switchNum
     */
    public static void push(int[] clocks, int switchNum) {
        for(int clock: switches[switchNum]) {
            clocks[clock] += 3;
            if(clocks[clock] == 15) clocks[clock] = 3;
        }
    }
}
