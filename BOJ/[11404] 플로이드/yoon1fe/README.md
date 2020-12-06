# [11404] 플로이드 - Java

###  :bus: 분류

> APSP
>
> 플로이드-와샬



### :bus: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static final int MAX = 10000000;
	static int N, M;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
		input();
		
		floyd_warshall();
		
		printArray();
	}

	public static void floyd_warshall() {
		for(int i = 1; i <= N; i++) {
			dist[i][i] = 0;
		}

		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
	}
	
	public static void printArray() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(dist[i][j] != MAX ? dist[i][j] + " " : 0 + " ");
			}
			System.out.println();
		}
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());

		dist = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], MAX);
		}
		
		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			dist[from][to] = dist[from][to] < weight ? dist[from][to] : weight;
		}
	}
}
```



### :bus: 풀이 방법

제목부터 플로이드 입니다. APSP(All Pairs Shortest Path) 알고리즘인 플로이드-와샬 알고리즘을 사용해서 푸는 문제입니다.

먼저 플로이드-와샬 알고리즘에 대해 간략히 설명하자면 음수 사이클이 없는 가중치 그래프에서 모든 정점들 간의 최단 거리를 구하는 알고리즘입니다. 알고리즘 형태는 단순합니다. 3중 포문만 쓰면 됩니다. 그렇기 때문에 시간복잡도는 O(V^3)이 됩니다.

 

문제 인풋을 보면 중복된 경로로 인풋이 들어옵니다. 아주 더럽지요. 그래서 입력받을 때 비교를 한 번 해줘야합니다.

 

위에서 말했듯이 굉장히 단순합니다. 디피적인 요소를 가미해서 dist[i][j] 가 dist[i][k] + dist[k][j] 보다 크면 dist[i][k] + dist[k][j]로 갱신해주면 끝입니다. 

 

마지막 출력할 때 만약 dist[i][j]의 값이 MAX라면, i에서 j로 가는 경로가 없단 의미이므로 0을 출력해주면 됩니다.



### :bus: 후기

감사합니다!!!