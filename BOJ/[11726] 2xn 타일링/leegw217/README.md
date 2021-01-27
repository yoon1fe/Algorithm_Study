# [11726] 2xn 타일링 - Java

###  :octocat: 분류

> 다이나믹 프로그래밍

### :octocat: 코드

```java
import java.util.Scanner;
public class week23_2xn타일링 {
	static int[] d = new int[1001];
	
	static int dp(int x) {
		if(x == 1) return 1;
		if(x == 2) return 2;
		if(d[x] != 0) return d[x];
		return d[x] = (dp(x-1) + dp(x-2)) % 10007;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(dp(sc.nextInt()));
	}
}
```

### :octocat: 풀이 방법

1. 2xN 크기 타일에 채우는 방법은 2x(N-1) 크기 타일 채운 갯수 + 2x(N-2) 크기 타일 채운 갯수

### :octocat: 후기

어우 요즘 프로젝트때문에 시간이없어서 오랜만에 풀었다.. ㅜ
