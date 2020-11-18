# [12015] 가장 긴 증가하는 부분 수열 2 - Java

###  :octocat: 분류

> DP
> 이분 탐색
> LIS

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 0번 값을 dp[0]에 넣고 1번 값부터 비교하면서 연산한다.
2. 만약 검사하는 값이 dp의 가장 마지막 값보다 크다면 뒤에 이어서 붙이고 작다면
이분 탐색을 이용해서 해당 값과 가장 근접한 수 중 크거나 같은 수를 찾아 바꿔준다.
3. 모든 값을 다 비교했을때 dp 배열의 길이가 수열의 길이가 된다.

### :octocat: 후기

싸피에서 dp 수업할때 배웠던 LIS 문제! 사실 기억안나서 수업자료 찾아보고 짰다..
난 이런 수학적인 문제가 너무 싫당 히히
