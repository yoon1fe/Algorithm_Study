# [19237] 어른 상어 - Java

###  :octocat: 분류

> 구현
> 
> BFS

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 손님들의 시작위치, 목적지위치, 시작~목적지까지 거리, 운행여부를 담은 객체 배열을 만든다.
2. 택시의 시작위치부터 BFS를 돌려 모든 좌표까지 거리를 계산한다.
3. 택시의 위치와 남은 손님들의 출발위치까지 거리 중 가장 짧은 거리를 찾아 택시 출발
4. 만약 손님을 태우러갈때나 손님을 태워서 목적지까지 가다가 연료가 다떨어지면 -1 출력
5. 손님을 목적지까지 운반할 수 있으면 연료에서 손님 태우러가는데 소비한 연료를 빼고 손님을 태워
목적지까지 가는데 소비한 연료를 더해주고 택시 위치를 목적지로 이동시킨다.
6. 모든 손님을 다 운반할 때까지 반복

### :octocat: 후기

쉬운 BFS 문제라고 생각해서 빨리 풀었는데 택시 위치에서 가장 짧은 손님까지 거리를 계산할때
만들어놓은 BFS 쓸려고 손님 수만큼 BFS를 반복 돌려서 계산했더니 시간초과가 떴다..
두 지점 사이 거리만 구해주는 BFS를 짜고 한 지점에서 모든 지점까지 거리를 계산해주는 BFS를 짜니
통과했다! 하루 두문제 이상씩 풀자고 마음먹었는데 못따라가는거 같아서 너무 미안하다..
최대한 빨리 따라잡아야겠다!
