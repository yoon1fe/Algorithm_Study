# [12100] 2048 (Easy) - Java

###  :octocat: 분류

> BFS
>
> 시뮬레이션



### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class week01_2048_Easy {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int answer = 0;
	static boolean[][] visited;
	
	static class MAP{
		int[][] map;
		int cnt = 0;
		int max = 0;
		
		public MAP(int[][] map, int cnt, int max) {
			this.map = map;
			this.cnt = cnt;
			this.max = max;
		}
	}
	
	static int findMax(int[][] map) { // 최대값 찾는 함수
		int max = 0;
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(max < map[i][j]) max = map[i][j];
			
		return max;
	}
	
	static int[][] moveBlock(int[][] m, int d){ // 블록 이동하는 함수
		int x=0, y=0;
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=1, k=N-2; j<N && k>=0; j++, k--) {
				switch(d) {
				case 0: x=j; y=i; break;
				case 1: x=i; y=k; break;
				case 2: x=k; y=i; break;
				case 3: x=i; y=j; break;
				}
				if(m[x][y] == 0) continue;
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				while(m[nx][ny] == 0) {
					nx += dx[d];
					ny += dy[d];
					if(nx<0||nx>=N||ny<0||ny>=N) {
						nx -= dx[d];
						ny -= dy[d];
						break;
					}
				}
				
				if(!visited[nx][ny] && m[nx][ny] == m[x][y]) {
					m[nx][ny] *= 2;
					visited[nx][ny] = true;
				}
				else {
					if(m[nx][ny] != 0) {
						nx -= dx[d];
						ny -= dy[d];
					}
					m[nx][ny] = m[x][y];
				}

				if(x!=nx||y!=ny)
					m[x][y] = 0;
			}
		}
		return m;
	}
	
	/*
	static void printMap(int[][] m) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}*/

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ArrayList<int[][]> mapList = new ArrayList<int[][]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<MAP> q = new LinkedList<MAP>();
		mapList.add(map);
		q.offer(new MAP(map,0,findMax(map)));
		
		while(!q.isEmpty()) {
			if(q.peek().cnt == 5) break; // 최대 5번 이동
			
			MAP m = q.poll();
			if(answer < m.max) answer = m.max; // 최댓값 갱신
			
			loop:
			for(int d=0; d<4; d++) {
				int[][] tempMap = new int[N][N];
				for(int i=0; i<N; i++) System.arraycopy(m.map[i], 0, tempMap[i], 0, m.map[i].length);
				tempMap = moveBlock(tempMap, d); // 블록 이동 함수
				//printMap(tempMap);
				
				for(int i=0; i<mapList.size(); i++) { // 이전에 나왔던 형태면 continue(중복체크)
					boolean flag = false;
					for(int k=0; k<N; k++) 
						if(!Arrays.equals(mapList.get(i)[k], tempMap[k])) flag = true;
					if(!flag) continue loop;
				}
				
				mapList.add(tempMap);
				q.offer(new MAP(tempMap,m.cnt+1,findMax(tempMap)));
			}
		}
		
		while(!q.isEmpty()) { // 5번 움직이고 난 결과물이 Queue에 남아있으니까 빼면서 최댓값 갱신
			MAP m = q.poll();
			if(answer < m.max) answer = m.max;
		}
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

현재 보드에서 4방향으로 이동시켰을 때 보드를 큐에 담아 BFS를 사용했습니다.
이동 후 보드상태가 이전에 나왔던 형태라면 넘기고 새로운 형태면 큐에 담았습니다.
중요 부분은 결국 보드를 4방향으로 이동시키는 함수라고 생각합니다.
처음에는 방향별로 함수를 분리해서 작성했었는데 겹치는 부분이 너무많아 합쳤습니다!
그리고 한번 합쳤던 숫자는 그 턴에 또 합쳐지면 안되기 때문에 visited 배열을 만들어 합쳐진 숫자인지 체크했습니다.
이후에는 이동이 완료된 보드에서 최댓값을 뽑아 계속 갱신해주면서 마지막 5번째 이동 후 결과를 출력해서 끝!

### :octocat: 후기

생각보다 빠른 시간에 풀었고 한번만에 정답이라고 나와서 기분이 너무나도 좋았던 문제였습니다~~
