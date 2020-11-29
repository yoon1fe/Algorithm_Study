import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class week13_ACMCraft {
	static int N, K, W;
	static int[] time;
	static List<Integer>[] adjList;
	static int[] indegree;
	
	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		int[] result = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			result[i] = time[i];
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(int i=0; i<adjList[p].size(); i++) {
				int node = adjList[p].get(i);
				result[node] = Math.max(result[node], result[p] + time[node]);
				indegree[node]--;
				if(indegree[node] == 0) q.offer(node);
			}
		}
		System.out.println(result[W]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			indegree = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) 
				time[i] = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[N+1];
			for(int i=0; i<=N; i++) adjList[i] = new ArrayList<Integer>();
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				adjList[x].add(y);
				indegree[y]++;
			}
			W = Integer.parseInt(br.readLine());
			topologicalSort();
		}
	}
}