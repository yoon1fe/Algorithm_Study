import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week16_비숍 {
	static int N;
	static int[][] board;
	static int[] dx = {-1, -1, 1, 1};
	static int[] dy = {-1, 1, -1, 1};
	static boolean[][] visited;
	static int total = 0;
	static int ans = 0;
	
	static void dfs(int x, int y, int cnt, int b, int w) {
		ans = Math.max(ans, cnt);
		int nx = x, ny = y;
		if(y >= N) {
			nx += 1;
			ny = (x%2 == 0)?b:w;
		}
		if(nx >= N) return;
		
		if(check(nx, ny)) {
			visited[nx][ny] = true;
			dfs(nx, ny+2, cnt+1, b, w);
			visited[nx][ny] = false;
		}
		dfs(nx, ny+2, cnt, b, w);
	}
	
	static boolean check(int x, int y) {
		if(board[x][y] == 0) return false;
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			for(int i=0; i<N; i++) {
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) return false;
				nx += dx[d];
				ny += dy[d];
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		// 검은색칸
		visited = new boolean[N][N];
		dfs(0, 0, 0, 1, 0);
		total += ans;
		ans = 0;
		// 흰색칸
		visited = new boolean[N][N];
		dfs(0, 1, 0, 0, 1);
		total += ans;
		System.out.println(total);
	}
}