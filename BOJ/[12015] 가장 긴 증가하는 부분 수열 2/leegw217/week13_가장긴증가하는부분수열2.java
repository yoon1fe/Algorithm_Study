import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week13_가장긴증가하는부분수열2 {
	static int N;
	static int[] arr;
	static int[] dp;
	
	static int lower_bound(int end, int n) {
		int start = 0;
		while(start < end) {
			int mid = (start + end) / 2;
			if(dp[mid] >= n) end = mid;
			else start = mid + 1;
		}
		return end;
	}
	
	static void solve() {
		dp[0] = arr[0];
		int idx = 0;
		for(int i=1; i<N; i++) {
			if(dp[idx] < arr[i]) dp[++idx] = arr[i];
			else {
				int lb = lower_bound(idx, arr[i]);
				dp[lb] = arr[i];
			}
		}
		System.out.println(idx+1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		dp = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		solve();
	}
}