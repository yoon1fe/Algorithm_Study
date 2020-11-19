# [1197] 최소 스패닝 트리 - Java

###  :bridge_at_night: 분류

> MST



### :bridge_at_night: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int to, weight;
		Node(int to, int weight){
			this.to = to; this.weight = weight;
		}
	}
	
	static int V, E;
	static List<Node>[] graph;
	public static void main(String[] args) throws Exception {
		input();
		
		// Prim's Alg
		System.out.println(prim());
	}
	
	public static long prim() {
		boolean[] v = new boolean[graph.length];
		int[] minEdge = new int[graph.length];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		int minVertex;
		long min, result = 0;
		minEdge[1] = 0;
		
		for(int c = 1;  c < graph.length; c++) {
			min = Long.MAX_VALUE;
			minVertex = 0;
			
			for(int i = 0; i < graph.length; i++) {
				if(!v[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			v[minVertex] = true;
			
			for(int i = 0; i < graph[minVertex].size(); i++) {
				Node next = graph[minVertex].get(i);
				if(!v[next.to] && next.weight < minEdge[next.to]) {
					minEdge[next.to] = next.weight;
				}
			}
		}
		
		return result;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		for(int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
	}
}
```



### :bridge_at_night: 풀이 방법

최소 스패닝 트리를 구하는 문제입니다. 크루스칼이나 프림 알고리즘을 사용하면 되겠져.

먼저 그래프는 리스트 배열로 표현했슴니다. 간선의 가중치도 표현해야 하니깐 Node 클래스를 만들어서 도착지와 weight를 저장해슴니다.

 

그 뒤는... 프림 알고리즘입니다. 이 방식으로 하면 O(V*E) 가 되는데, 인접한 애들 중에서 가장 작은 애를 찾을 때 우선순위 큐를 쓰면 O(E*logV) 로 줄일 수 있습니다.

 

### :bridge_at_night: 후기

여유가 되면 크루스칼이랑 프림 알고리즘을 더 공부하고 정리해야게씀니다.

감사합니다~!!~