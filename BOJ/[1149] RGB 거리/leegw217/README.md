# [1149] RGB 거리 - Java

###  :octocat: 분류

> DP

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. n번째 B를 칠하는 값중 최솟값은 n-1에 R을 칠한경우와 G를 칠한경우 중 더 작은 값을 가진 경우에 B를 더한 값이다.
2. 같은 방법으로 n번째에 R, G, B를 각각 칠한 경우 최솟값이 정답이 된다.

### :octocat: 후기

dp는 아무리 오래 생각해도 안보이면 말짱 도루묵인듯..
