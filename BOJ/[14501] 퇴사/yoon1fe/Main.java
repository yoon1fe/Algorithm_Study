import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] t = new int[N + 1];
		int[] p = new int[N + 1];
		int[] dp = new int[N + 2];		// i 번째 날까지의 최대 이익
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1; i <= N + 1; i++) {
			dp[i] = dp[i-1];
			for(int j =  1; j < i; j++) {
				if(j + t[j] == i) dp[i] = Math.max(dp[i], dp[j] + p[j]);
			}
		}
		System.out.println(dp[N+1]);
	}
}