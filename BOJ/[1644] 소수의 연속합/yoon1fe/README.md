## [1644] 소수의 연속합 - Java

### :two: 분류

> 에라토스테네스의 체
>
> 투 포인터



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[N + 1];
		List<Integer> primeList = new ArrayList<>();
		
		isNotPrime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(isNotPrime[i]) continue;
			for(int j = i + i; j <= N; j += i) {
				isNotPrime[j] = true;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(!isNotPrime[i]) primeList.add(i);
		}
		
		System.out.println(solve(N, primeList));
	}	
	
	public static int solve(int n, List<Integer> list) {
		if(n == 1) return 0;
		if(n == 2) return 1;
		
		int left = 0, right = 1;
		int sum = list.get(0) + list.get(1), answer = list.contains(n) == true ? 1 : 0;
		
		while(left < right) {
			if(sum == n) {
				answer++;
				sum += list.get(++right);
			}else if(sum < n) {
				sum += list.get(++right);
			}else {
				sum -= list.get(left++);
			}
		}
		
		return answer;
	}	
}
```



### :five: 풀이 방법

N이 주어졌을 때 소수의 연속합으로 N을 만들 수 있는 경우의 수를 구하는 문제입니다.

에라토스테네스의 체로 소수들을 구하고 투 포인터로 간단하게 풀었습니다.



먼저 소수는 N이하 까지의 범위에서만 구하면 됩니다.

에라토스테네스의 체는 간단하게만 알아봅시다.

isNotPrime 배열을 썼습니다. 이름이 아주 얄궂은 이유는 소수를 찾았을 때(i) 이 i의 배수는 모두 소수가 아니기 때문이져. 고거 true로 둘려고 isNotPrime으로 이름지었습니다. 맨 첨에 boolean 배열이 false로 초기화되니깐....

어차피 i를 찾았을 때 배수부터 소수가 아니라는걸 체크할거기 때문에 Math.sqrt(N) 만큼만 i를 찾아보면 됩니당.

 

이제 소수 리스트를 구했으니 얘를 갖고 부분합을 구해줍니다. 연속된 소수의 합들로만 만들 수 있기 때문에 투 포인터로 가능합니다.

while 반복문의 조건을 left < right 로 둬서 맨 처음에 left = 0, right = 1로 주고 시작했습니다. 그리고 n이 리스트에 들어 있다면 answer에 1을 쁘라스해야겠죵. 

 

여기서 사알짝 주의해야 할 점이 있습니다. 제가 바보같이 0~1을 보겠다고 첨부터 해놨기 때문에 1과 2가 인풋으로 들어올 때는 list.get(1)이 없기 때문임니당. 그래서 그냥 바로 리턴해줘씀니당 헤헤.

 

### :seven: 후기

아주 간단한 투 포인터 문제였습니당. 연습 합시다 연습!!

감사합니다 ~~~~ 