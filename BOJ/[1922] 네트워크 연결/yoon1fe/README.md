# [1922] 네트워크 연결 - Java

###  :computer: 분류

> MST



### :computer: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int to, weight;
		
		Node(int to, int weight){
			this.to = to; this.weight = weight;
		}
		
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static List<Node>[] graph;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(prim());
	}
	
	public static int prim() {
		int answer = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();	// 시작 정점에서 인접한 간선 중 가장 작은 가중치의 간선
        Queue<Integer> q = new LinkedList<>();			// 시작 정점
        boolean[] v = new boolean[N+1];
        
        q.add(1);	// 1번부터 시작

        while (!q.isEmpty()) {
            int cur = q.poll();
            v[cur] = true;

            for (Node adj : graph[cur]) {
                if (!v[adj.to]) {
                    pq.add(adj);
                }
            }
            
            while (!pq.isEmpty()) {
                Node next = pq.poll();
                
                if (!v[next.to]) {
                    q.add(next.to);
                    v[next.to] = true;
                    answer += next.weight;
                    
                    break;
                }
            }
        }
		
		return answer;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

		while(M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
	}
}
```



### :computer: 풀이 방법

컴퓨터와 컴퓨터를 연결하는 네트워크... 

MST 임니당.

마찬가지로 프림 알고리즘으로 풀었습니다.

요번에는 인접한 애들 중에서 간선이 가장 작은 애를 찾을 때 N 타임으로 찾지 않고 우선순위 큐를 써서 풀었습니다.

 

q에 정점들을 넣고 빼면서 거기서 인접한 애들을 보는 식입니다. 이 때 방문하지 않은 정점들만 체크해야겠지용!

 

N 타임으로 찾지 않고 우선순위 큐를 사용한다면 시간 복잡도가 O(E*V) (또는 O(V^2)) 에서 O(E*logV) 가 됩니다. V만큼 보는걸 logV 로 주는 셈이지용.



### :computer: 후기

연습 많이 해서 프림 알고리즘을 체화해야게씀니다.

감사합니다!!!