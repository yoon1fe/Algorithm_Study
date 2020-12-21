import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week19_플로이드 {
	static int N, M;
	static int[][] adjMatrix;
	static int max = 10000001
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) continue;
				adjMatrix[i][j] = max;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = Math.min(adjMatrix[a][b], c);
		}
		
		for(int k=1; k<=N; k++) 
			for(int i=1; i<=N; i++) 
				for(int j=1; j<=N; j++) 
					adjMatrix[i][j] = Math.min(adjMatrix[i][k]+adjMatrix[k][j], adjMatrix[i][j]);
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(adjMatrix[i][j] >= max) System.out.print("0 ");
				else System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}