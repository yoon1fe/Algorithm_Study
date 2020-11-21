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