import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;

		public Dir(int y, int x) {
			this.y = y; this.x = x;
		}
	}
	
	static int[][] map;
	static int N, L, R;
	static boolean[][] v;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	public static boolean bfs(Dir s) {
		List<Dir> union = new ArrayList<>();
		Queue<Dir> q = new LinkedList<>();
		int newPopulation = 0;
		
		q.offer(s);
		newPopulation = map[s.y][s.x];
		v[s.y][s.x] = true;
		union.add(s);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next)) continue;
				int diff = Math.abs(map[cur.y][cur.x] - map[next.y][next.x]);
				if(v[next.y][next.x] || L > diff || R < diff) continue;
				
				v[next.y][next.x] = true;
				q.offer(next);
				union.add(next);
				newPopulation += map[next.y][next.x];
			}
		}

		newPopulation /= union.size();
		
		for(Dir d : union) map[d.y][d.x] = newPopulation; 
		
		return union.size() > 1 ? true : false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean flag = false;
			v = new boolean[N][N];

			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(v[i][j]) continue;
					flag = bfs(new Dir(i, j)) | flag;
				}
			}
			if(!flag) break;
			answer++;
		}
		
		System.out.println(answer);
	}
}