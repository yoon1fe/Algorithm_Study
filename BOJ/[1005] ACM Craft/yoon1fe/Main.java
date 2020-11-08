import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, W;
	static int[] buildTime;
	static List<Integer>[] graph;
	static int[] inDegree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			input();
			
			System.out.println(solve());
		}
	}
	
	public static int solve() {
		int[] total = Arrays.copyOf(buildTime, N);
		// 위상 정렬
		List<Integer> topol = topologicalSort();
		
		// 시작점에서부터 찾기
		for(int i = 0; i < N; i++) {
			int cur = topol.get(i);
			
			for(int j = 0; j < graph[cur].size(); j++) {
				int next = graph[cur].get(j);
				
				total[next] = Math.max(total[next], total[cur] + buildTime[next]);
			}
		}
		
		return total[W];
	}
	
	public static List<Integer> topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(inDegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			list.add(cur);
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(--inDegree[next] == 0) q.offer(next);
			}
		}
		
		return list;
	}

	public static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		
		buildTime = new int[N];
		graph = new List[N];
		inDegree = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			buildTime[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine() , " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			graph[from].add(to);
			inDegree[to]++;
		}
		
		W = Integer.parseInt(br.readLine()) - 1;
	}
	
}