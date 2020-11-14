import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer>[] graph;
	static int[] time;
	static int[] inDegree;
	
	public static void main(String[] args) throws Exception {
		input();

		solve();
	}
	
	public static void solve() {
		int[] answer = new int[N + 1];
		
		// 위상 정렬
		List<Integer> topol = topologicalSort();
		
		// 더해가기
		for(int i = 0; i < topol.size(); i++) {
			int cur = topol.get(i);
			answer[cur] += time[cur];
			
			for(int j = 0; j < graph[cur].size(); j++) {
				int next = graph[cur].get(j);
				
				if(answer[next] < answer[cur]) answer[next] = answer[cur];
			}
		}
		
		for(int i = 1; i <= N; i++) System.out.println(answer[i]);
	}
	
	public static List<Integer> topologicalSort() {
		List<Integer> ret = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.offer(i); 
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			ret.add(cur);
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(--inDegree[next] == 0) q.offer(next);
			}
		}
		
		return ret;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		time = new int[N + 1];
		inDegree = new int[N + 1];
		
		for(int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			
			while(true) {
				int from = Integer.parseInt(st.nextToken());
				if(from == -1) break;
				graph[from].add(i);
				inDegree[i]++;
			}
		}
	}
}