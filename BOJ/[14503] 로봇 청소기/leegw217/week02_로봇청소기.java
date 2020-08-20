import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week02_로봇청소기 {
	static class Robot{
		int x, y;
		int dir;
		
		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int answer=0;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		Robot rb = new Robot(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		loop:
		while(true) {
			int checkDir = rb.dir;
			if(!visited[rb.x][rb.y]) {
				answer++;
				visited[rb.x][rb.y] = true;
			}
			for(int i=0; i<4; i++) {
				if(checkDir == 0) checkDir = 3;
				else checkDir -= 1;
				int nx = rb.x + dx[checkDir];
				int ny = rb.y + dy[checkDir];
				if(map[nx][ny]==1 || visited[nx][ny]) continue;
				rb.x = nx;
				rb.y = ny;
				rb.dir = checkDir;
				continue loop;
			}
			if(checkDir == 1) checkDir = 3;
			else if(checkDir == 0) checkDir = 2;
			else checkDir -= 2;
			if(map[rb.x+dx[checkDir]][rb.y+dy[checkDir]] == 1) break;
			rb.x += dx[checkDir];
			rb.y += dy[checkDir];
		}
		System.out.println(answer);
	}
}