# [14888] 연산자 끼워넣기 - Java

###  :octocat: 분류

> 시뮬레이션

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class week02_연산자끼워넣기 {
	static int N;
	static int[] numArr;
	static int[] oper;
	static int[] result;
	static boolean[] visited;
	static int max = -1000000001;
	static int min = 1000000001;
	
	static int calc(int x, int y, int oper) {
		switch(oper) {
		case 1:	return x+y;
		case 2:	return x-y;
		case 3:	return x*y;
		case 4:	return x/y;
		}
		return 0;
	}
	
	static void makeOper(int cnt) {
		if (cnt == result.length) {
			int x = numArr[0];
			for(int i=0; i<N-1; i++) 
				x = calc(x,numArr[i+1],result[i]);
			if(max < x) max = x;
			if(min > x) min = x;
			return;
		}
		for (int i = 0; i < oper.length; i++) {
			if (visited[i] == false) {
				result[cnt] = oper[i];
				visited[i] = true;
				makeOper(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numArr = new int[N];
		oper = new int[N-1];
		result = new int[N-1];
		visited = new boolean[N-1];
		for(int i=0; i<N; i++)
			numArr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		for(int i=1; i<=4; i++) {
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k; j++) 
				oper[idx++] = i;
		}
		makeOper(0);
		System.out.println(max);
		System.out.println(min);
	}
}
```

### :octocat: 풀이 방법

1. 연산자를 중복없는 순열 구하는 방법으로 가능한 모든 경우를 구한다.
2. 순열별로 계산해서 나온 결과값에 대한 최댓값과 최솟값을 구한다.
3. 끝!

### :octocat: 후기

쉬운 문제였숨니다!
