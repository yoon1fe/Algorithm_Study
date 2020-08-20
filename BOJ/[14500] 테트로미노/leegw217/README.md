# [14502] 연구소 - Java

###  :octocat: 분류

> 시뮬레이션
>
> DFS

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class week02_테트로미노 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int max = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static List<int[]> stack = new ArrayList<int[]>();
	
	static void dfs(int[] p, int cnt, int sum) {
		sum += map[p[0]][p[1]];
		if(cnt == 3) {
			if(max < sum) max = sum;
			return;
		}
		visited[p[0]][p[1]] = true;
		stack.add(p);
		for(int i=0; i<stack.size(); i++) {
			int tx = stack.get(i)[0];
			int ty = stack.get(i)[1];
			for(int j=0; j<4; j++) {
				int nx = tx + dx[j];
				int ny = ty + dy[j];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny]) continue;
				dfs(new int[] {nx,ny}, cnt+1, sum);
			}
		}
		stack.remove(stack.size()-1);
		visited[p[0]][p[1]] = false;
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
				dfs(new int[] {i,j},0,0);
			}
		}
		System.out.println(max);
	}
}
```

### :octocat: 풀이 방법

맵을 순회하며 DFS를 이용해서 해당 칸에서 만들 수 있는 모든 테트로미노 블럭을 체크한다.

### :octocat: 후기

계속 짜다가 도저히 못짜겠어서 상윤이 코드를 보고 짰다ㅜ DFS 어려워..
