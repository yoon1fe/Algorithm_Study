import java.io.*;
import java.util.*;

public class Main {
	static final int MAX = 10000000;
	static int N, M;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		input();
		
		floyd_warshall();
		
		printArray();
	}

	public static void floyd_warshall() {
		for(int i = 1; i <= N; i++) {
			dist[i][i] = 0;
		}

		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
	}
	
	public static void printArray() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(dist[i][j] != MAX ? dist[i][j] + " " : 0 + " ");
			}
			System.out.println();
		}
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());

		dist = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], MAX);
		}
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			dist[from][to] = dist[from][to] < weight ? dist[from][to] : weight;
		}
	}
}