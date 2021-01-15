# [2839] 설탕 배달 - Java

###  :octocat: 분류

> 그리디

### :octocat: 코드

```java
import java.util.Scanner;
public class week22_설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int num = sc.nextInt();
		int q = num/5;
		for(int i=q; i>=0; i--) {
			int r = num - i*5;
			if(r%3 == 0) {
				answer += i;
				answer += r/3;
				break;
			}
		}
		if(answer != 0) System.out.println(answer);
		else System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. 5로 나눈 값을 하나씩 줄여가면서 계산
2. 5로 나눈 값이 가장 클때 나머지가 3으로 나눠진다면 그게 가장 적은 수 봉지

### :octocat: 후기

수우우학!
