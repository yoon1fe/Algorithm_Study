import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week16_네트워크연결 {
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
		StringTokenizer st;
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
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