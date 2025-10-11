public class _6_2_boggle {
    public static char[][] board = {
        {'t', 'h', 'i', 's'},
        {'w', 'a', 't', 's'},
        {'o', 'a', 'h', 'g'},
        {'f', 'g', 'd', 't'}
    };
    public static void main(String[] args) {
        System.out.println( hasWord(0, 0, "this") ); // true
    }

    public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static boolean hasWord(int y, int x, String word) {
        if(word.length() == 0) return true;
        if(!inRange(y, x)) return false;
        if(board[y][x] != word.charAt(0)) return false;
        if(word.length() == 1) return true;
        for(int dir = 0; dir < 8; dir++) {
            if(hasWord(y + dy[dir], x + dx[dir], word.substring(1))) {
                return true;
            }
        }
        return false;
    }

    public static boolean inRange(int y, int x) {
        return 0 <= y && y < board.length && 0 <= x && x < board[0].length;
    }
}

// 시간복잡도: O(8^L) (L: 단어 길이)