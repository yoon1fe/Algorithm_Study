# [11404] 플로이드 - Java

###  :octocat: 분류

> 플로이드 와샬

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week19_플로이드 {
	static int N, M;
	static int[][] adjMatrix;
	static int max = 10000001
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjMatrix = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) continue;
				adjMatrix[i][j] = max;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = Math.min(adjMatrix[a][b], c);
		}
		
		for(int k=1; k<=N; k++) 
			for(int i=1; i<=N; i++) 
				for(int j=1; j<=N; j++) 
					adjMatrix[i][j] = Math.min(adjMatrix[i][k]+adjMatrix[k][j], adjMatrix[i][j]);
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(adjMatrix[i][j] >= max) System.out.print("0 ");
				else System.out.print(adjMatrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
```

### :octocat: 풀이 방법

1. 인접행렬을 만든다. 이때 i == j인경우를 제외하고 max값으로 초기화한뒤 만든다.
2. 모든 정점에 대해서 다른 모든 정점으로 가는데 걸리는 최솟값을 플로이드 와샬을 이용해 구한다.
3. 만든 인접행렬을 출력한다. 이때 max값인 경우 0으로 출력한다.

### :octocat: 후기

플로이드 와샬은 빨리 짰는데 max값을 너무 작게해서 틀리고 도달 못하는 경우를 생각 안해줘서 또 틀리고 ㅜㅜ
