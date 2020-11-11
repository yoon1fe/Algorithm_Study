# [16637] 괄호 추가하기 - Java

###  :octocat: 분류

> 구현
> 
> DFS

### :octocat: 코드

```java
import java.util.Scanner;
public class Main_16637 {
	static int N;
	static int[] num;
	static char[] oper;
	static boolean[] par;
	static int max = Integer.MIN_VALUE;
	
	static void makePar(int idx) {
		if(idx == N/2) {
			execute();
			return;
		}
		
		if(idx==0 || par[idx-1]==false) {
			par[idx] = true;
			makePar(idx+1);
		}
		par[idx] = false;
		makePar(idx+1);
	}
	
	static void execute() {
		int result = 0;
		for(int i=0; i<N/2+1; i++) {
			if(par[i] == false) {
				if(i==0 || par[i-1]==false)
					result = calc(result, oper[i], num[i]);
			} else {
				int n = calc(num[i], oper[i+1], num[i+1]);
				result = calc(result, oper[i], n);
			}
		}
		max = Math.max(max, result);
	}
	
	static int calc(int a, char op, int b) {
		int result = 0;
		switch(op) {
		case '+': result = a + b; break;
		case '-': result = a - b; break;
		case '*': result = a * b;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N/2+1];
		oper = new char[N/2+1];
		par = new boolean[N/2+1];
		String s = sc.next();
		int idx = 0;
		oper[0] = '+';
		for(int i=0; i<N; i=i+2) {
			num[idx++] = s.charAt(i) - '0';
			if(i+1 != N) oper[idx] = s.charAt(i+1);
		}
		makePar(0);
		System.out.println(max);
	}
}
```

### :octocat: 풀이 방법

1. 주어진 식에서 숫자만 담은 배열(num), 연산자만 담은 배열(oper)을 만든다.
2. 숫자 앞에 괄호가 있는지 없는지 여부를 담을 boolean 배열(par)을 만든다.
3. 배열 3개 다 크기는 N/2+1개로 동일하며 연산자 0번칸은 +로 넣고
괄호 N/2번칸은 False로 채운다.
4. 괄호채우는건 바로 전 숫자앞에 괄호가 있으면 안넣고, 없으면 괄호를 넣거나
안넣거나로 dfs를 돌린다.
5. 0부터 N/2까지 괄호 여부를 보고 괄호가 없으면 바로 앞에 괄호가 있는지 검사해서
없으면 해당 칸의 숫자와 결과를 해당 칸의 연산자로 계산한다.
6. 현재 칸에 괄호가 있으면 현재 칸 숫자를 다음칸 숫자와 다음칸 연산자로 계산한
결과를 현재까지 결과값과 현재 칸 연산자로 계산한다.
7. 최댓값 찾기

### :octocat: 후기

괄호 채우는건 쉽게 했는데 계산하는 부분에서 뇌가 굳었는지 잘 안됐다;
그래서 종이에 표 그려가면서 어떤식으로 계산해야할지 정하면서 했다!
그래도 한번만에 통과했고 시간도 생각보다 얼마 안걸렸다 히히
