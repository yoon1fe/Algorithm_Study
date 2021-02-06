import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N+2];
		int[] time = new int[N+2];
		int[] dp = new int[N+2];
		StringTokenizer st = null;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(i + time[i] <= N + 1) {
				dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i] + price[i]);
				ans = Math.max(dp[i+time[i]], ans);
			}
            dp[i+1] = Math.max(dp[i], dp[i+1]);
            ans = Math.max(dp[i+1], ans);
		}
		
		System.out.println(ans);
	}
}