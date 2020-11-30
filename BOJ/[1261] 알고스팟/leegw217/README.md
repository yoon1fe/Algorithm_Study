# [1261] 알고스팟 - Java

###  :octocat: 분류

> BFS
> 
> 우선순위 큐

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. BFS를 이용해서 (N, M) 지점까지 이동한다.
2. 벽을 만나면 지나간 벽의 수를 1 증가시키고 큐에 담는다.
3. 큐에서 지나간 벽의 수가 적은 순으로 뽑아서 진행시킨다.
4. (N, M) 지점에 도달했을때 지나간 벽의 수를 출력하면 가장 적은 수가 나오게 된다.

### :octocat: 후기

전에 한번 풀어봤던 문제라서 빨리 풀 수 있었다!
