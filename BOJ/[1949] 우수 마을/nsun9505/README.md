# [1949] 우수마을 - JAVA

## 분류
> 트리 DP
> 
> DP

## 코드
```java
package BOJ1949;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] graph;
	static boolean[] isVisited;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		isVisited = new boolean[N+1];
		dp = new int[2][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			dp[1][i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		DFS(1);
		
		sb.append(Math.max(dp[0][1], dp[1][1]));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void DFS(int cur) {
		isVisited[cur] = true;
		
		for(int next : graph[cur]) {
			if(isVisited[next])
				continue;
			
			DFS(next);
			dp[0][cur] += Math.max(dp[0][next], dp[1][next]);
			dp[1][cur] += dp[0][next];
		}
	}
}
```

## 문제 풀이
분류를 보고 트리 DP를 파악했습니다.

DP를 풀 떄는 (1) 테이블 정의하기, (2) 점화식 세우기, (3) 초기값 채우기
   - 바킹독님 강의에서 배웠습니다.

DP로 어떻게 풀까 고민하면서 N*N 2차원 배열로 풀까 했지만 식을 어떻게 뽑지라고 고민하다가 

구글링으로 다른 사람의 아이디어를 참고했습니다.

1. 테이블 정의하기
   - 하나의 마을은 우수 마을로 선정되거나 아니거나 둘 중 하니입니다.
   - 그래서 dp[0][1~N]은 각 마을이 일반 마을인 아닌 경우
   - dp[1][1~N]은 각 마을이 우수 마을인 경우

주의해야 할 조건
   - A와 B가 인접했을 때, A가 우수마을이면 B는 우수마을일 수 없음.
   - 반대로, B가 우수마을이면 A는 우수마을일 수 없음.
   - 그리고 A와 B가 둘 다 일반마을일 수 있다.

위 세 가지 조건을 생각하면서 X 마을이 우수마을일 때와 일반 마을일 때를 구분지을 수 있습니다.
   - X 마을이 우수 마을이라면 인접한 마을은 모두 일반 마을이므로 다음과 같습니다.
        ```
            dp[1][X] += dp[0][next]
        ```
        - next는 X와 인접한 마을을 의미합니다.
   - X 마을이 우수 마을이 아니라면 인접한 마을은 우수 마을이거나 일반 마을일 수 있으므로 다음과 같이 계산할 수 있습니다.
        ```
            dp[0][X] += Math.max(dp[0][next], dp[1][next])
        ```
    - 우수 마을의 주민 수의 총합이므로 X 마을에 더해주면 됩니다.

탐색은 DFS로 탐색이 가능합니다.

DFS로 탐색을 하면서 테이블에 값을 채우면 됩니다.

마지막으로 dp[0][1], dp[1][1] 둘 중에서 가장 큰 값을 답으로 출력하면 됩니다.

## 후기
트리 디피 첫 풀이!