# [1644] 소수의 연속합 - Java

###  :octocat: 분류

> 투 포인터
> 
> 에라토스테네스의 체

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class week13_소수의연속합 {
	static List<Integer> prime = new ArrayList<Integer>();
	static int answer = 0;
	
	static void makePrime(int N) {
		boolean[] number = new boolean[N+1];
		number[0] = number[1] = true;
		
		for(int i=2; i*i<=N; i++) 
			if(!number[i]) 
				for(int j=i*i; j<=N; j+=i) number[j] = true;
		
		for(int i=2; i<=N; i++)
			if(!number[i]) prime.add(i);
	}
	
	static void findContinuity(int N) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = prime.get(0);
		while(true) {
			if(startIdx == endIdx && endIdx == prime.size()-1) break;
			if(sum <= N) {
				endIdx++;
				if(sum == N) answer++;
				sum += prime.get(endIdx);
			} else {
				sum -= prime.get(startIdx);
				startIdx++;
			}
		}
		if(prime.get(prime.size()-1) == N) answer++;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N == 1) {
			System.out.println(0);
		} else {
			makePrime(N);
			findContinuity(N);
			System.out.println(answer);
		}
	}
}
```

### :octocat: 풀이 방법

1. 에라토스테네스의 체를 이용해 입력받은 숫자 이하의 소수를 모두 구한다.
2. 시작점과 끝점을 두고 범위 내 숫자 합을 구하면서 연산한다.
3. 범위 내 숫자 합이 입력받은 숫자보다 작으면 끝점을 한칸 뒤로 한칸 민다.
4. 입력받은 숫자와 같으면 끝점을 한칸 뒤로 밀고 정답을 +1한다.
5. 입력받은 숫자보다 크면 시작점에 해당하는 수를 합에서 빼주고 시작점을 뒤로 한칸 민다.
6. 시작점과 끝점이 구한 소수의 가장 마지막 값에 도달하면 입력값이 소수인지 검사하고 종료한다.

### :octocat: 후기

에라토스테네스의 체를 전에 한번 봤었어서 답을 빨리 구할 수 있었다!!
처음에 디피문제인줄 알았는데 투포인터로도 쉽게 풀 수 있었다!
