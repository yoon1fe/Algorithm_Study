# [2023] 신기한 소수 - Java

###  :octocat: 분류

> DFS
>
> 백트래킹

### :octocat: 코드

```java
import java.util.Scanner;
public class week16_신기한소수 {
	static int N;
	static int[] number;
	static boolean[] visited;
	static StringBuilder sb;
	
	static void dfs(int cnt) {
		if(cnt == N) {
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(cnt == 0 && i == 0) continue;
			StringBuilder temp = new StringBuilder();
			temp.append(sb.toString());
			temp.append(i);
			if(isPrime(Integer.parseInt(temp.toString()))) {
				sb.append(i);
				visited[i] = true;
				dfs(cnt+1);
				visited[i] = false;
				sb.deleteCharAt(cnt);	
			}
		}
	}
	
	static boolean isPrime(int num) {
		if(num <= 1) return false;
		if(num == 2) return true;
		if(num % 2 == 0) return false;
		for(int i=3; i*i<=num; i++)
			if(num % i == 0) return false;
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		number = new int[10];
		visited = new boolean[10];
		sb = new StringBuilder();
		for(int i=0; i<10; i++) number[i] = i;
		dfs(0);
	}
}
```

### :octocat: 풀이 방법

1. 0부터 9까지 숫자를 넣어가면서 dfs를 돌린다.
2. 첫번째 자리는 0이 오면 안되니 걸러준다.
3. 숫자를 하나씩 넣어보고 소수인지 검사해서 소수면 다음 dfs로 넘어간다.
4. 작은 숫자부터 넣었기 때문에 자동으로 오름차순 출력된다.

### :octocat: 후기

이런 문제만 코테에서 백트래킹으로 나왔으면 좋겠다.. 그럼 정말 행복하겠지?
