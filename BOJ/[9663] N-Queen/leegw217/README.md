# [9663] N-Queen - Java

###  :octocat: 분류

> DFS
>
> 백트래킹

### :octocat: 코드

```java
import java.util.Scanner;
public class week15_NQueen {
	static int N;
	static int[] col;
	static int ans = 0;
	
	static void queens(int i) {
		if(i == N) {
			ans++;
			return;
		}		
		for(int j=0; j<N; j++) {
			col[i] = j;
			if(promising(i))
				queens(i+1);
		}
	}
	
	static boolean promising(int i) {
		int k = 0;
		boolean promising = true;
		while(k < i && promising) {
			if((col[i] == col[k]) || Math.abs(col[i]-col[k])==Math.abs(i-k))
				promising = false;
			k++;
		}
		return promising;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N];
		queens(0);
		System.out.println(ans);
	}
}
```

### :octocat: 풀이 방법

1. 일차원 배열을 만들어 각 원소의 index가 열, 원소 값이 행을 나타내게 한다.
2. 재귀로 퀸을 놓는데 퀸이 놓을 수 있는 위치인가 검사를 하면서 퀸을 놓는다.
3. N개의 퀸을 놓으면 카운트를 증가시키고 종료

### :octocat: 후기

학교 알고리즘 수업때 들었던 기억이나서 오랜만에 수업자료를 뒤져보고 짰다
유관우 교수님.. 그립읍니다..
