# [1197] 최소 스패닝 트리 - Java

###  :octocat: 분류

> 스패닝트리

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week15_최소스패닝트리 {
	static int V, E;
	static List<Edge>[] adjList;
	
	static class Edge implements Comparable<Edge>{
		int node;
		int dist;
		Edge(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	static void prim() {
		int ans = 0;
		int cnt = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[V+1];
		for(Edge first : adjList[1]) pq.add(first);
		visited[1] = true;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(visited[edge.node]) continue;
			visited[edge.node] = true;
			ans += edge.dist;
			
			for(Edge next : adjList[edge.node]) 
				if(!visited[next.node]) pq.add(next);
			if(++cnt == V) break;		
		}
		System.out.println(ans);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[V+1];
		for(int i=1; i<=V; i++) adjList[i] = new ArrayList<Edge>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
			adjList[b].add(new Edge(a, c));
		}
		prim();
	}
}
```

### :octocat: 풀이 방법

1. 최대 간선수가 많기 때문에 프림 알고리즘을 사용한다.
2. 1번 정점부터 시작해서 1번 정점과 연결된 모든 간선을 우선순위 큐에 담는다.
3. 우선순위 큐에서 가중치가 가장 작은 간선을 뽑고 해당 노드와 연결된 간선들을
우선순위 큐에 담는다.
4. 모든 정점을 다 방문할때까지 반복한다.

### :octocat: 후기

오랜만에 스패닝트리 만들기!
간선이 많으면 프림이 더 유리하다 들었다!
