# [18809] Gaaaaaaaaaarden - JAVA

## 분류
> 구현
>
> 시뮬레이션

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] isVisited;
	static char[][] color;
	static int[][] dist;
	static Queue<Element> queue = new LinkedList<>();
	static ArrayList<Pos> position = new ArrayList<>();
	static int N, M, R, G;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		color = new char[N][M];
		isVisited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					position.add(new Pos(i, j));
			}
		}
		solution(0, 0, 0, new ArrayList<Element>());
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	public static void solution(int gCnt, int rCnt, int idx, ArrayList<Element> list) {
		if(gCnt == G && rCnt == R) {
			if(gCnt + rCnt > 10)
				return;
			
			queue.clear();
			for(int i=0; i<N; i++) {
				Arrays.fill(dist[i], 0);
				Arrays.fill(isVisited[i], false);
				Arrays.fill(color[i], '-');
			}
			
			int cnt = 0;
			for(Element elem : list) {
				queue.offer(elem);
				isVisited[elem.row][elem.col] = true;
				color[elem.row][elem.col] = elem.color;
			}
			
			while(!queue.isEmpty()) {
				Element elem = queue.poll();
				if(color[elem.row][elem.col]== 'F' )
					continue;
				
				for(int dir = 0; dir < 4; dir++) {
					int nx = elem.row + dx[dir];
					int ny = elem.col + dy[dir];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					
					if(map[nx][ny] == 0)
						continue;
					
					if(isVisited[nx][ny]) {
						if(color[nx][ny] == 'F')
							continue;
						if(color[nx][ny] != elem.color  && (elem.dist + 1) == dist[nx][ny]) {
							cnt++;
							color[nx][ny] = 'F';
						}
						continue;
					}
					
					queue.offer(new Element(nx, ny, elem.color, elem.dist + 1));
					isVisited[nx][ny] = true;
					dist[nx][ny] = elem.dist + 1;
					color[nx][ny] = elem.color;
				}
			}
			
			ans = Math.max(ans, cnt);
			return;
		}
		
		if(idx >= position.size())
			return;
		Pos pos = position.get(idx);
		Element elem = new Element(pos.row, pos.col, 'G', 0);
		list.add(elem);
		solution(gCnt+1, rCnt, idx+1, list);
		elem.color = 'R';
		solution(gCnt, rCnt+1, idx+1, list);
		list.remove(elem);
		solution(gCnt, rCnt, idx+1, list);
		
	}
	
	
	static class Element{
		int row;
		int col;
		char color;
		int dist;
		public Element(int row, int col, char color, int dist) {
			this.row = row;
			this.col = col;
			this.color = color;
			this.dist = dist;
		}
	}
	
	static class Pos{
		int row;
		int col;
		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
		
	}
}
```

## 문제 풀이
배양액을 놓을 수 있는 곳에 대해서 주어진 배양액을 모두 써서 배치를 합니다.
   - 여기서 배양액을 놓을 수 있는 위치를 찾기 위해서 DFS마다 이중포문을 돌리면 시간초과가 뜹니다.
   - 그래서 배양액을 놓을 수 있는 곳을 입력 받을 때 미리 list로 만들어 놓아서 이 list에 대해서 DFS를 돌리면 됩니다.

배양액을 모두 다 써서 배치시켰다면 BFS를 돌립니다.
   - 거리가 같고, 서로 다른 색이고 아직 합쳐지지 않았다면 꽃을 피웁니다.
   - 거리가 같고, 사로 다른 색이지만 이미 합쳐진 경우에는 넘어갑니다.

주의할 점은 합쳐진 위치에 먼저 도착한 것이 더 이상 진행하지 않도록 해야 합니다.
   - 그래서 queue에서 꺼냈을 때 자기 위치가 꽃이 피워진 자리라면 4방향 탐색을 더 이상 진행하지 않고 끝냅니다.

## 후기
오늘도 파이팅!!!