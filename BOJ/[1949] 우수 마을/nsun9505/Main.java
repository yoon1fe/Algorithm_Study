import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] isVisited;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		isVisited = new boolean[N+1];
		dp = new int[2][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			dp[1][i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		DFS(1);
		
		sb.append(Math.max(dp[0][1], dp[1][1]));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void DFS(int cur) {
		isVisited[cur] = true;
		
		for(int next : graph[cur]) {
			if(isVisited[next])
				continue;
			
			DFS(next);
			dp[0][cur] += Math.max(dp[0][next], dp[1][next]);
			dp[1][cur] += dp[0][next];
		}
	}
}