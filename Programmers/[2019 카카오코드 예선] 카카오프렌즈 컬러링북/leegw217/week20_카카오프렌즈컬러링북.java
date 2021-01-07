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