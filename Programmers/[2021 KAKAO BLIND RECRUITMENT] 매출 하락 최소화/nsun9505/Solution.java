import java.util.ArrayList;

public class Solution {
    static ArrayList<Element>[] graph;
    static boolean[] isVisited;
    static int[][] dp;
    static int N;
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        N = sales.length;
        graph = new ArrayList[N+1];
        dp = new int[N+1][2];
        isVisited = new boolean[N+1];
        Element[] arr = new Element[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dp[i][1] = sales[i-1];
            arr[i] = new Element(i, sales[i-1]);
        }

        for(int i=0; i<links.length; i++)
            graph[links[i][0]].add(arr[links[i][1]]);

        DFS(1);

        answer = Math.min(dp[1][0], dp[1][1]);

        return answer;
    }

    public static void DFS(int cur){
        isVisited[cur] = true;

        if(graph[cur].size() == 0)
            return;

        int sum = 0;
        int cnt = 0;
        for(Element next : graph[cur]){
            if(isVisited[next.vertex])
                continue;

            DFS(next.vertex);
            if(dp[next.vertex][0] > dp[next.vertex][1]){
                sum += dp[next.vertex][1];
                cnt++;
            } else {
                sum += dp[next.vertex][0];
            }
        }

        dp[cur][1] += sum;
        if(cnt > 0)
            dp[cur][0] = sum;
        else {
            int min = Integer.MAX_VALUE;
            for (Element next : graph[cur])
              min = Math.min(min, dp[next.vertex][1] - dp[next.vertex][0]);
            dp[cur][0] = sum + min;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(	new int[]{5, 6, 5, 3, 4}, new int[][]{{2, 3}, {1, 4}, {2, 5}, {1, 2}}));
    }

    static class Element{
        int vertex;
        int val;

        public Element(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }
    }
}
