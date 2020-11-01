import java.io.*;
import java.util.*;

public class Main{
	static final int MIN = 9999999;
	static int[] population;
	static boolean[] isSelected;
	static int N;
	static int ans = MIN;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	public static boolean bfs(ArrayList<Integer> area) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] v = new boolean[N+1];
		q.offer(area.get(0));
		v[area.get(0)] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i< graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if(!area.contains(next) || v[next]) continue;
				
				q.offer(next);
				v[next] = true;
			}
		}
		for(int i = 0; i < area.size(); i++) if(!v[area.get(i)]) return false;
		
		return true;
	}
	
	public static void solve(ArrayList<Integer> area1, ArrayList<Integer> area2) {
		if(!bfs(area1) || !bfs(area2)) return;

		int sum1 = 0, sum2 = 0;
		for(int i : area1) sum1 += population[i];
		for(int i : area2) sum2 += population[i];
		ans = Math.min(ans, Math.abs(sum1 - sum2));		
	}
	
	public static void makeSubset(int cnt) {
		if(cnt == N) {
			ArrayList<Integer> area1 = new ArrayList<Integer>();
			ArrayList<Integer> area2 = new ArrayList<Integer>();
			
			for(int i = 1; i<= N; i++) {
				if(isSelected[i]) area1.add(i);
				else area2.add(i);
			}
			if(area1.size() == N || area1.size() == 0) return;
			solve(area1, area2);
			
			return;
		}
		
		isSelected[cnt+1] = true;
		makeSubset(cnt+1);
		isSelected[cnt+1] = false;
		makeSubset(cnt+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		population = new int[N+1];
		isSelected = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		graph.add(new ArrayList<>());
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer> t = new ArrayList<Integer>();
			int num =  Integer.parseInt(st.nextToken());
			for(int j = 0; j< num; j++) {
				t.add(Integer.parseInt(st.nextToken()));
			}
			graph.add(t);
		}
		
		makeSubset(0);
		
		if(ans == MIN) ans = -1;
		System.out.println(ans);	
	}
}