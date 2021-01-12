# [1057] 토너먼트 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Scanner;
public class week21_토너먼트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int round = 1;
		if(A > B) {
			int temp = A;
			A = B;
			B = temp;
		}
		while(true) {
			if(A%2!=0 && B%2==0 && A<B && B-A==1) {
				System.out.println(round);
				break;
			}
			A = (A%2 == 0) ? A/2 : A/2+1;
			B = (B%2 == 0) ? B/2 : B/2+1;
			round++;
		}
	}
}
```

### :octocat: 풀이 방법

1. 김지민는 A번, 임한수는 B번일때 A가 B보다 크면 서로 숫자를 바꿔준다.
2. while문 안에서 A와 B가 만났는지 검사한다.
3. 안만났으면 A와 B를 재배정 (짝수면 /2, 홀수면 /2+1) 후 라운드 증가
4. 만나면 라운드 출력

### :octocat: 후기

뭐야 N은 왜필요한거지
