# [11404] 플로이드 - JAVA

## 구현
> 플로이드 와샬

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long [][] graph;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		solution();
		printAnswer();
	}
	
	public static void printAnswer() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(graph[i][j] == Integer.MAX_VALUE || graph[i][j] == 0) System.out.print(0 + " ");
				else System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void solution() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i == j || i == k || k == j) continue;
					graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new long[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				graph[i][j] = Integer.MAX_VALUE;
		
		for(int i=1; i<=N; i++)
			graph[i][i] = 0;
				
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u][v] = Math.min(graph[u][v], w);
		}
	}
}
```

## 문제 풀이
플로이드 와샬을 공부하고 문제를 풀었습니다!

간단하게 플로이드 와샬만 사용하면 풀 수 있는 문제겸 플로이드 와샬을 배울 수 있는 좋은 문제!
- 덕분에 플로이드 와샬을 복습할 수 있어서 좋았습니다.

대신! 주의할 점!
**시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.**
- 이것을 안 보면 틀립니다.. 나는 틀렸습니다..

로직은 플로이드 와샬을 사용해서 각 노드에서 모든 노드로 가는 최소값을 구합니다.
- 대신, 세팅할 때는 입력되는 도시-도시를 연결하는 노선이 하나 이상이 아닐 수 있기에 그 중에서 가장 최솟값을 세팅하도록 **graph[u][v] = Math.min(graph[u][v], w)**를 사용했습니다!
- 플로이드 와샬로 정답을 구하면, 답을 출력하면 됩니다.
- 답은 graph[i][j]가 Integer.MAX_VALUE이거나 0이면 0을 출력하고, 아니면 graph[i][j]를 출력하면 됩니다.

## 후기
아... 문제를 잘 봅시다.. 맞왜틀에는 다 이유가 있습니다. 내가 부주의한 점이 크다는 거..

문제를 제발 잘 봅시다ㅠ