# [9095] 1, 2, 3 더하기 - Java

###  :octocat: 분류

> DP

### :octocat: 코드

```java
import java.util.Scanner;
public class week23_123더하기 {
	static int[] d = new int[10001];
	
	static int dp(int x) {
		if(x == 1) return 1;
		if(x == 2) return 2;
		if(x == 3) return 4;
		if(d[x] != 0) return d[x];
		return d[x] = dp(x-1) + dp(x-2) + dp(x-3);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++) {
			int n = sc.nextInt();
			System.out.println(dp(n));
		}
	}
}
```

### :octocat: 풀이 방법

1. n이 1일때 1, n이 2일때 2, n이 3일때 4
2. n이 3이상일때는 n-1일때 + n-2일때 + n-3일때 하면 됨

### :octocat: 후기

비교적 아주 쉬운 점화식!
