/*
 * TSP (Traveling Salesman Problem)
 * 
 * 어떤 나라에 n(2 <= n <= 10)개의 도시가 있다.
 * 한 영업 사원이 이 도시들을 모두 방문하고 다시 출발지로 돌아오려고 한다.
 * 이때 가능한 경로 중 가장 짧은 경로의 길이는?
 */

import java.util.List;

public class _6_7_tsp {
    public static int n = 4;
    public static double[][] dist = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    public static void main(String[] args) {
        List<Integer> path = new java.util.ArrayList<>();
        path.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        System.out.println( shortestPath(path, visited, 0) ); // 80.0
    }

    /**
     * @description 모든 도시를 방문하고 다시 출발지로 돌아오는 가장 짧은 경로의 길이를 반환
     * @param path 도시 0부터 살펴본다. path에는 지금까지 방문한 도시의 번호가 순서대로 들어있다.
     * @param visited
     * @param currentLength
     * @return
     */
    public static double shortestPath(List<Integer> path, boolean[] visited, double currentLength) {
        if(path.size() == n) {
            return currentLength + dist(path.get(path.size() - 1), path.get(0));
        }
        double ret = Double.MAX_VALUE;
        for(int next = 1; next < n; next++) {
            if(!visited[next]) {
                visited[next] = true;
                path.add(next);
                ret = Math.min(ret, shortestPath(path, visited, currentLength + dist(path.get(path.size() - 2), next)));
                visited[next] = false;
                path.remove(path.size() - 1);
            }
        }
        return ret;
    }
    
    public static double dist(int a, int b) {
        return dist[a][b];
    }
}

//시간복잡도: O(n!) (n: 도시 수)