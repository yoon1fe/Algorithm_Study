import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class week02_퇴사 {
	static int N;
	static int[][] schedule;
	static int max = 0;
	static List<Integer>[] adj;
	
	static class Point{
		int idx;
		int sum = 0;
		public Point(int idx, int sum) {
			this.idx = idx;
			this.sum = sum;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		schedule = new int[N][2];
		adj = (ArrayList<Integer>[]) new ArrayList[N];
		for(int i=0; i<N; i++)
			adj[i] = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
			for(int j=i+schedule[i][0]; j<N; j++)
				adj[i].add(j);
		}
		
		for(int k=0; k<N; k++) {
			Queue<Point> q = new LinkedList<Point>();
			if(k+schedule[k][0] > N) continue;
			q.offer(new Point(k,schedule[k][1]));
			
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(max < p.sum) max = p.sum;
				
				for(int i=0; i<adj[p.idx].size(); i++) {
					if(adj[p.idx].get(i)+schedule[adj[p.idx].get(i)][0] > N) continue;
					q.offer(new Point(adj[p.idx].get(i), p.sum+schedule[adj[p.idx].get(i)][1]));
				}
			}
		}
		System.out.println(max);
	}
}
