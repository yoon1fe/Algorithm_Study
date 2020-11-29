import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] graph;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs(1));
	}
	
	public static int bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] v = new boolean[N + 1];
		int answer = 0;
		
		q.offer(s);
		v[s] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(v[next] == false) {
					answer++;
					v[next] = true;
					q.offer(next);
				}
			}
		}
		
		return answer;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
		
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
	}
}
