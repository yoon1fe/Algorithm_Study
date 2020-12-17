import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long [][] graph;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		solution();
		printAnswer();
	}
	
	public static void printAnswer() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(graph[i][j] == Integer.MAX_VALUE || graph[i][j] == 0) System.out.print(0 + " ");
				else System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void solution() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i == j || i == k || k == j) continue;
					graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new long[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				graph[i][j] = Integer.MAX_VALUE;
		
		for(int i=1; i<=N; i++)
			graph[i][i] = 0;
				
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u][v] = Math.min(graph[u][v], w);
		}
	}
}