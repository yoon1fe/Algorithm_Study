import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week14_알고스팟 {
	static class Point implements Comparable<Point> {
		int x, y;
		int wall;
		
		Point(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}
		
		@Override
		public int compareTo(Point o) {
			return wall - o.wall;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=1; j<=M; j++)
				map[i][j] = c[j-1] - '0';
		}
		// BFS
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		boolean[][] v = new boolean[N+1][M+1];
		q.offer(new Point(1,1,0));
		v[1][1] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if(p.x == N && p.y == M) {
				System.out.println(p.wall);
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if(nx < 1 || nx > N || ny < 1 || ny > M) continue;
				if(v[nx][ny]) continue;
				if(map[nx][ny] == 1) {
					q.offer(new Point(nx,ny,p.wall+1));
					v[nx][ny] = true;
				} else {
					q.offer(new Point(nx,ny,p.wall));
					v[nx][ny] = true;
				}
			}
		}
	}
}