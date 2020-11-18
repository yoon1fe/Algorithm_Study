# [17472] 다리 만들기 2 - Java

###  :octocat: 분류

> 그래프
> BFS
> 프림

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week13_다리만들기2 {
	static int N, M;
	static int mapNum = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[][] adjMatrix;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void bfs(int x, int y) {
		mapNum++;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		map[x][y] = mapNum;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int dir=0; dir<4; dir++) {
				int nx = p[0] + dx[dir];
				int ny = p[1] + dy[dir];
				
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny] || map[nx][ny] == 0) continue;
				
				q.offer(new int[] {nx,ny});
				visited[nx][ny] = true;
				map[nx][ny] = mapNum;
			}
		}
	}

	static void makeBridge() {
		for(int x=0; x<N; x++) {
			for(int y=0; y<M; y++) {
				if(map[x][y] == 0) continue;
				int mapN = map[x][y];
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					if(map[nx][ny] != 0) continue;
					int len = 1;
					while(true) {
						nx += dx[d];
						ny += dy[d];
						if(nx<0||nx>=N||ny<0||ny>=M) break;
						if(map[nx][ny] == mapN) break;
						if(map[nx][ny]>0 && map[nx][ny]!=mapN) {
							if(len == 1) break; // 길이 1인 다리는 안됨
							if(adjMatrix[mapN][map[nx][ny]] != 0) { // 기존에 연결된 다리가 있으면
								if(adjMatrix[mapN][map[nx][ny]] > len) // 새로 연결한 다리가 더짧으면 교체
									adjMatrix[mapN][map[nx][ny]] = adjMatrix[map[nx][ny]][mapN] = len;
							} else { // 연결된 다리가 없으면
								adjMatrix[mapN][map[nx][ny]] = adjMatrix[map[nx][ny]][mapN] = len;
							}
							break;
						}
						len++;
					}
				}
			}
		}
	}
	
	static int makeMST() {
		int[] minEdge = new int[mapNum+1];
		boolean[] v = new boolean[mapNum+1];
		int result = 0;
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		
		for(int c=0; c<mapNum; c++) {
			int min = Integer.MAX_VALUE;
			int minNo = 1;
			
			for(int i=1; i<=mapNum; i++) {
				if(!v[i] && min>minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			v[minNo] = true;
			result += min;
			
			for(int i=1; i<=mapNum; i++) {
				if(!v[i] && adjMatrix[minNo][i]>0 && minEdge[i]>adjMatrix[minNo][i])
					minEdge[i] = adjMatrix[minNo][i];
			}
		}
		
		for(int k=1; k<=mapNum; k++) {
			if(v[k] == false)
				return -1;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				bfs(i,j);
			}
		}
		adjMatrix = new int[mapNum+1][mapNum+1];
		makeBridge();
		System.out.println(makeMST());
	}
}
```

### :octocat: 풀이 방법

1. BFS를 이용해서 각 섬들에 번호를 매긴다.
2. 각 섬을 정점으로 하는 그래프를 인접행렬을 이용해 만든다. 이때 인접행렬 내에 들어가는 값은 단순 0,1이
아니라 연결된 다리의 길이를 나타낸다.
3. 모든 섬의 모든 땅을 순회하면서 모든 섬을 연결하는 다리들을 설치한다. 이때 길이가 1인경우, 이미 더 짧은
다리가 건설된경우를 제외한 가장 짧은 길이의 다리를 건설한다.
4. 모든 다리가 건설되어 다 연결이 되었다면 프림 알고리즘을 사용해서 모든 섬을 연결하는 다리 길이의 최솟값을 구한다.

### :octocat: 후기

이 문제도 싸피에서 풀었던 문제였다. MST를 만드는 알고리즘 중 프림을 써서 풀었던 문제다 히히
그래프 알고리즘들은 코테에도 자주 나올 수 있으니 연습을 더해겠다!
