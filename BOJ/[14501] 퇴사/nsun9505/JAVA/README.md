# [14501] 퇴사 - JAVA

## 분류
> DP

## 코드
```java
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
```

## 문제 풀이
전형적인 DP 문제 또는 조합?!으로 풀 수 있는 문제입니다.

이미 DP로 풀렸던 기억이 있었지만 오래돼서 한 번 풀어봤습니다.

먼저 i번째 날에 상담을 할 경우 i + time[i]날 값과 비교합니다.
   - i번째 날에 상담을 할 수 있다는 것은 dp[i] + price[i]입니다.
   - 그러면 i + time[i] 날에 누적된 합과 비교하여 가장 큰 값으로 dp[i + tiem[i]]를 갱신해주면 됩니다.

위와 같은 경우는 무조건 i번째 날에 상담을 하는 것입니다.
   - 이 경우에는 i일 전까지 상담한 것과 i일에 상담하지 않고 i+1에 상담을 한 경우가 i일에 상담을 한 것보다 더 큰 값을 가질 수 있습니다.
   - 이 예시가 예제에 잘 나와있으므로 금방 캐치할 수 있었습니다!
   - 그리고 i번째 날에 상담을 하거나 상담하지 않을 수 있다는 것을 알게 되었고 그대로 두 번째 if문으로 나타내어 풀 수 있었습니다.

## 후기
토요일은 풀었던 문제 풀어보는 날~