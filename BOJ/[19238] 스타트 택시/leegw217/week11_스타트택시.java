import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week11_스타트택시 {
	static int N, M, P;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int tx, ty;
	static int[][] v;
	
	static class Customer {
		int sx, sy, ex, ey;
		int p;
		boolean isalive;
		Customer(int sx, int sy, int ex, int ey, int p, boolean isalive) {
			this.sx = sx; this.sy = sy; this.ex = ex; this.ey = ey;
			this.p = p; this.isalive = isalive;
		}
	}
	
	static int findFast(int sx, int sy, int ex, int ey) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N+1][N+1];
		q.offer(new int[] {sx,sy,0});
		visited[sx][sy] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			if(p[0]==ex && p[1]==ey) return p[2];
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<1||nx>N||ny<1||ny>N) continue;
				if(map[nx][ny]==1 || visited[nx][ny]) continue;
				q.offer(new int[] {nx,ny,p[2]+1});
				visited[nx][ny] = true;
			}
		}
		return -1;
	}
	
	static void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<int[]>();
		v = new int[N+1][N+1];
		for(int i=0; i<=N; i++) Arrays.fill(v[i], Integer.MAX_VALUE);
		q.offer(new int[] {sx,sy});
		v[sx][sy] = 0;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<1||nx>N||ny<1||ny>N) continue;
				if(map[nx][ny]==1) continue;
				if(v[nx][ny] <= v[p[0]][p[1]]+1) continue;
				q.offer(new int[] {nx,ny});
				v[nx][ny] = v[p[0]][p[1]]+1;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		tx = Integer.parseInt(st.nextToken());
		ty = Integer.parseInt(st.nextToken());
		Customer[] customer = new Customer[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int p = findFast(sx,sy,ex,ey);
			if(p == -1) {System.out.println(-1); return;}
			customer[i] = new Customer(sx,sy,ex,ey,p,true);
		}
		for(int i=0; i<M; i++) {
			int minp = Integer.MAX_VALUE;
			int minj = 0;
			bfs(tx, ty);
			for(int j=0; j<M; j++) {
				if(!customer[j].isalive) continue;
				if(minp >= v[customer[j].sx][customer[j].sy]) {
					if(minp == v[customer[j].sx][customer[j].sy]) {
						if(customer[minj].sx >= customer[j].sx) {
							if(customer[minj].sx == customer[j].sx
									&& customer[minj].sy < customer[j].sy) continue;
							else {
								minp = v[customer[j].sx][customer[j].sy];
								minj = j;
							}
						}
					} else {
						minp = v[customer[j].sx][customer[j].sy];
						minj = j;
					}
				}
			}
			if(P - minp <= 0 || P - minp - customer[minj].p < 0) {
				System.out.println(-1); 
				return;
			}
			P = P - minp + customer[minj].p;
			customer[minj].isalive = false;
			tx = customer[minj].ex;
			ty = customer[minj].ey;
		}
		System.out.println(P);
	}
}