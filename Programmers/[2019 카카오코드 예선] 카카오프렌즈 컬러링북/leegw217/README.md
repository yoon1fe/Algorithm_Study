# [2017 카카오코드 예선] 카카오프렌즈 컬러링북 - Java

###  :octocat: 분류

> BFS

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
public class week20_카카오프렌즈컬러링북 {
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};
	boolean[][] visited;
	
	int bfs(int x, int y, int m, int n, int color, int[][] picture) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
				if(picture[nx][ny] == 0) continue;
				if(visited[nx][ny]) continue;
				if(picture[nx][ny] != color) continue;
				q.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
				cnt++;
			}
		}
		return cnt;
	}
	
	public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		if(picture[i][j] == 0) continue;
        		if(visited[i][j]) continue;
        		numberOfArea++;
        		int cnt = bfs(i, j, m, n, picture[i][j], picture);
        		maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
        	}
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 모든 좌표를 검사하는데 0인 좌표와 이미 방문한 좌표는 제외한다.
2. 방문한적 없는 색이면 거기서 시작해서 BFS를 돌린다. 이때 같은 색인 영역 크기를 구한다.
3. 모든 좌표를 검사한뒤 영역 개수와 가장 큰 영역 넓이를 구한다.

### :octocat: 후기

가장 기초적인 BFS문제!
