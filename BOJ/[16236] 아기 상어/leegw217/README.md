# [16236] 아기 상어 - Java

###  :octocat: 분류

> 구현
> BFS
> 우선순위 큐

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
public class week09_아기상어 {
	static int N;
	static int[][] map;
	static Shark shark;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Shark{
		int x, y;
		int size;
		int eat;
		int walk;
		Shark(int x, int y, int size, int eat, int walk){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.walk = walk;
		}
	}
	
	static void eatFish(int[] fish) {
		map[shark.x][shark.y] = 0; 
		shark.x = fish[0];
		shark.y = fish[1];
		map[shark.x][shark.y] = 9; 
		shark.eat++;
		if(shark.eat == shark.size) {
			shark.size++;
			shark.eat = 0;
		}
		shark.walk += fish[2];
	}
	
	static boolean bfs() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<int[]> fish = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		});
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {shark.x, shark.y, 0});
		visited[shark.x][shark.y] = true;
		int limit = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			if(p[2] > limit) break;
			
			for(int dir=0; dir<4; dir++) {
				int nx = p[0] + dx[dir];
				int ny = p[1] + dy[dir];
				
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] > shark.size) continue;
				if(map[nx][ny] < shark.size && map[nx][ny] != 0) {
					limit = p[2];
					fish.add(new int[] {nx,ny,p[2]+1});
				}
				q.offer(new int[] {nx, ny, p[2]+1});
				visited[nx][ny] = true;
			}
		}
		if(fish.size() == 0) return false;
		eatFish(fish.peek());
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) 
					shark = new Shark(i, j, 2, 0, 0);
			}
		}
		while(true) {
			if(!bfs()) break;
		}
		System.out.println(shark.walk);
	}
}
```

### :octocat: 풀이 방법

1. 상어의 좌표, 사이즈, 먹은 물고기 수, 이동거리를 담은 클래스를 만든다.
2. 상어를 이동시켜 만난 먹을 수 있는 물고기들의 위치를 우선순위 큐에 담고
가장 가까운 위치 물고기를 먹는다.
3. 더이상 물고기를 못먹을 때 까지 BFS를 반복한다.

### :octocat: 후기

알고리즘 수업때 한번 풀었었고 싸피하면서 한번 더 풀어봤던 문제! 그대로 제출!
