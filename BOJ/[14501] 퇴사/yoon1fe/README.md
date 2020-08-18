# [14501] 퇴사 - Java

###   :office:분류

> DP
>



### :office: 코드

```java
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
```



###  :office:풀이 방법

딱 보자마자 DP로 풀면 되겠구나 싶었습니다. 하지만 N의 최댓값이 15기 때문에 완전 탐색해서 풀어도 되고 DFS로 풀어도 됩니다.



`dp[i]` 는 i 번째 날에 얻을 수 있는 최대 이익입니다.

i=1 부터 `N+1`까지 보면서 그 날에 얻을 수 있는 최대 이익을 구해줍니다.

참고로 N번째 날에 1일짜리 상담은 할 수 있기 때문에 `N+1`까지 반복문을 돌려야 합니다.

그리고 `j=1` 부터 i 전까지 봐주면서 만약 `j + t[j]` (j번째 일이 끝나는 날)이 i와 같다면 `dp[i]`를 업데이트 해주면 됩니다..

 

### :office: 후기

DP 문제 많이 풀어봐야겠습니다....