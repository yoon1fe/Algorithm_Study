import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week24_RGB거리 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] color = new int[3];
		int[][] dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) color[j] = Integer.parseInt(st.nextToken());
			if(i == 0) for(int j=0; j<3; j++) dp[i][j] = color[j];
			else {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + color[0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + color[1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + color[2]; 
			}
		}
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}
}