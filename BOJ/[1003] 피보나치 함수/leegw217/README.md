# [1003] 피보나치 함수 - Java

###  :octocat: 분류

> DP

### :octocat: 코드

```java
import java.util.Scanner;
public class week23_피보나치함수 {
	static int[][] dp = new int[41][2];
	
	static int[] fibonacci(int x) {
		if(dp[x][0] == 0 && dp[x][1] == 0) {
			dp[x][0] = fibonacci(x-1)[0] + fibonacci(x-2)[0];
			dp[x][1] = fibonacci(x-1)[1] + fibonacci(x-2)[1];
		}
		return dp[x];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		for(int i=0; i<T; i++) {
			int n = sc.nextInt();
			System.out.println(fibonacci(n)[0]+" "+fibonacci(n)[1]);
		}
	}
}
```

### :octocat: 풀이 방법

1. dp 배열에는 0 호출 횟수와 1 호출 횟수를 저장한다.
2. n이 0일때 0이 1번 호출, 1이 0번 호출되고 n이 1일때 0이 0번, 1이 1번 호출된다.
3. n이 2 이상일때는 dp 배열에 저장된값은 곧바로 호출하고 아니면 n-1번째 배열과 n-2번째 배열을 합친 값을 저장한다.

### :octocat: 후기

dp배열을 2차원으로 써야한다는걸 좀 늦게 깨닳아버렸다.
