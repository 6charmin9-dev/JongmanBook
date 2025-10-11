import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _6_5_boardcover {
/*
# 입력
첫줄: 테스트 케이스 수 C(C <= 30)
각 테스트 케이스의 첫줄: 두 개의 정수 H, W(1 <= H, W <= 20). 
다음 H줄에 각 W글자로 게임판의 모양
# 은 검은 칸, . 은 흰 칸
흰칸의 수는 50을 넘지 않음

# 출력
한 줄에 하나씩 흰 칸을 모두 덮는 방법의 수 

# input
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########

# output
0
2
1514
 */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while(C-- > 0) {
            String[] hw = br.readLine().split(" ");
            int H = Integer.parseInt(hw[0]);
            int W = Integer.parseInt(hw[1]);
            char[][] board = new char[H][W];
            int emptyCount = 0;
            for(int i = 0; i < H; i++) {
                board[i] = br.readLine().toCharArray();
                for(int j = 0; j < W; j++) {
                    if(board[i][j] == '.') {
                        emptyCount++;
                        board[i][j] = 0;
                    } else{
                        board[i][j] = 1;
                    }
                }
            }
            if(emptyCount % 3 != 0) {
                System.out.println(0);
                continue;
            }
            System.out.println(cover(board));
        }
    }

    public static int[][][] coverType = {
        {{0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {1, 1}},
        {{0, 0}, {1, 0}, {1, -1}}
    };

    /**
     * @description (y, x)를 기준으로 type 모양의 덮개를 delta에 따라 덮거나 제거
     * @param board
     * @param y
     * @param x
     * @param type
     * @param delta +1: 덮기, -1: 제거
     * @return 덮개를 덮거나 있으면 true, 없으면 false
     */
    public static boolean set(char[][] board, int y, int x, int type, int delta) {
        boolean ok = true;
        for(int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];
            if(ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length) {
                ok = false;
            } else if((board[ny][nx] += delta) > 1) {
                ok = false;
            }
        }
        return ok;
    }

    public static int cover(char[][] board) {
        int y = -1, x = -1;
        // 가장 왼쪽 위에 있는 빈 칸 찾기
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if(y != -1) break;
        }
        if(y == -1) return 1; // 기저 사례: 모든 칸이 덮여 있음
        int ret = 0;
        for(int type = 0; type < 4; type++) {
            if(set(board, y, x, type, 1)) {
                ret += cover(board);
            }
            set(board, y, x, type, -1); // 덮개 제거
        }
        return ret;
    }
}

//시간복잡도: O(4^(k/3))) (k: 빈 칸의 수)