# [1463] 1로 만들기 - Java

###  :octocat: 분류

> DP

### :octocat: 코드

```java
import java.util.Scanner;
public class week22_1로만들기 {
	static int[] d = new int[1000001];
	
	static int dp(int x) {
		if(x == 1) return 0;
		if(d[x] != 0) return d[x];
		int div3 = Integer.MAX_VALUE;
		int div2 = Integer.MAX_VALUE;
		if(x % 3 == 0) div3 = dp(x/3) + 1;
		if(x % 2 == 0) div2 = dp(x/2) + 1;
		return d[x] = Math.min(Math.min(div2, div3), dp(x-1) + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(dp(N));
	}
}
```

### :octocat: 풀이 방법

1. 3으로 나누는 연산, 2로 나누는 연산, 1빼는 연산을 dp로 최솟값 비교하면서 구한다.

### :octocat: 후기

싸피할때 풀었던 문제!
