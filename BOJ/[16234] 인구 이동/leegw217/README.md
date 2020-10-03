# [16234] 인구 이동 - Java

###  :octocat: 분류

> BFS

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class week08_인구이동 {
	static boolean checkMove(int[][] m, int l, int r) {
		int n = m.length;
		List<int[]> answer = new ArrayList<int[]>();
		int[][] visited = new int[n][n];
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] != 0) continue;
				int cnt = 1;
				idx++;
				Queue<int[]> q = new LinkedList<int[]>();
				visited[i][j] = idx;
				q.offer(new int[] {i,j});
				int sum = m[i][j];
				
				while(!q.isEmpty()) {
					int[] p = q.poll();
					for(int d=0; d<4; d++) {
						int nx = p[0] + dx[d];
						int ny = p[1] + dy[d];
						if(nx<0||nx>=n||ny<0||ny>=n) continue;
						if(visited[nx][ny] != 0) continue;
						int cha = Math.abs(m[p[0]][p[1]] - m[nx][ny]);
						if(l<=cha && cha<=r) {
							visited[nx][ny] = idx;
							cnt++;
							sum += m[nx][ny];
							q.offer(new int[] {nx,ny});
						}
					}
				}
				if(cnt == 1) {
					visited[i][j] = -1;
					idx--;
				} else answer.add(new int[] {cnt, sum});
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] > 0) {
					int[] t = answer.get(visited[i][j]-1);
					m[i][j] = t[1]/t[0];
				}
			}
		}
		if(answer.size() == 0) return false;
		else return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = sc.nextInt();
		
		while(true) {
			if(!checkMove(map, L, R)) break;
			answer ++;
		}
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

1. bfs로 하루에 이동할 수 있는 국가들을 표시한다.
2. 각 연합별로 인구를 이동시킨다.
3. 더이상 이동할 수 없으면 종료

### :octocat: 후기

처음에 연합마다 이동하는 횟수를 증가시켜줬는데 하루에 이동하는 연합들
다 합쳐서 +1이였다.. 문제좀 제대로 써라!! 
