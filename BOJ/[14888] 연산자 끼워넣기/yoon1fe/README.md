# [14888] 연산자 끼워넣기 - Java

###  :robot: 분류

> 순열
>
> 완전 탐색



### :robot: 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[] A;
	static int[] inputOp = new int[4];
	static int[] op;
	static int[] isSelected;
	static int max = -1000000001;
	static int min = 1000000001;
	
	static void calc() {
		List<Integer> expr = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			expr.add(A[i]);
			if(i == N - 1) break;
			expr.add(op[i]);
		}
		
		int result = expr.get(0);
		
		for(int i = 1; i < (op.length * 2); i+=2) {
			switch(expr.get(i)) {
			case 0: result += (expr.get(i+1)); break;
			case 1: result -= (expr.get(i+1)); break;
			case 2: result *= (expr.get(i+1)); break;
			case 3: result /= (expr.get(i+1)); break;
			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}
	
	static void perm(int cnt) {
		if(cnt == N-1) {
			calc();
			return;
		}
		
		for(int i = 0; i< 4; i++) {
			if(isSelected[i] < 1) continue;
			isSelected[i]--;
			op[cnt] = i;
			perm(cnt+1);
			isSelected[i]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		A = new int[N];
		op = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) inputOp[i] = Integer.parseInt(st.nextToken());
		isSelected = inputOp.clone();
		
		perm(0);
				
		System.out.println(max + "\n" + min);
	}
}
```



### :robot: 풀이 방법

입력으로 주어진 연산자들을 갖고 식을 만들어서 그 식의 최대/최솟값을 구하는 문제입니다.

완전 탐색으로 순열을 다 만들어서 풀어도 되고, 백트래킹으로 풀어도 됩니다.

순을 이용해서 가능한 연산자들의 조합을 모두 만들고나서 이를 토대로 계산하는 방식으로 풀었습니다.

동일한 연산자가 여러 개가 있을 수 있기 때문에 isSelected 배열을 boolean이 아니라 남은 연산자 개수로 생각하고 써먹어야 합니다.

연산자 조합을 다 만들고 calc() 메소드에서 이를 계산합니다. 짜다보니 쪼금 복잡하게 짰습니다. expr이란 변수에 식을 완성시켜서 넣었습니다. 이러면 연산자는 항상 짝수번째 인덱스에만 존재하므로 저렇게 계산했습니다.



### :robot: 후기

반년전에 풀었던 백트래킹 코드가 훨배 좋네여.. ^^;;

```c++
#include <iostream>
#include <algorithm>

using namespace std;

int N;
int input[11];
int op[4] = { 0, };
int maxval = -1000000001, minval = 1000000001;

void dfs(int s, int result) {
	int t = 0;

	if (s == N - 1) {
		maxval = max(maxval, result);
		minval = min(minval, result);
		return;
	}
	
	for (int i = 0; i < 4; i++) {
		if (op[i] == 0)
			continue;
		switch (i) {
		case 0:	t = result + input[s + 1]; break;
		case 1:	t = result - input[s + 1]; break;
		case 2:	t = result * input[s + 1]; break;
		case 3:	t = result / input[s + 1]; break;
		}
		
		op[i]--;
		dfs(s + 1, t);
		op[i]++;
	}
}

int main() {
	cin >> N;

	for (int i = 0; i < N; i++) cin >> input[i];
	for (int i = 0; i < 4; i++) cin >> op[i];

	dfs(0, input[0]);
	cout << maxval << endl << minval;

	return 0;
}
```

