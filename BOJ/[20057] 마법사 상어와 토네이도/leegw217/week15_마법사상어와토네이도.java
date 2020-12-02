import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week15_마법사상어와토네이도 {
	static int N;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int ans = 0;
	// 토네이도로 좌표 이동
	static void tornado() {
		int nx = N/2;
		int ny = N/2;
		int dir = -1;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<2; j++) {
				dir = (dir+1) % 4;
				for(int w=0; w<i; w++) {
					nx += dx[dir];
					ny += dy[dir];
					flutterSand(nx, ny, dir);
					if(nx==0 && ny==0) {
						System.out.println(ans);
						System.exit(0);
					}
				}				
			}
		}
	}
	// 모래 흩뿌리기
	static void flutterSand(int x, int y, int dir) {
		int totalSand = map[x][y];
		// 5%
		int nx = x + (dx[dir] * 2);
		int ny = y + (dy[dir] * 2);
		int sand = map[x][y] * 5 / 100;
		moveSand(nx, ny, sand);
		totalSand -= sand;
		// 10%, 7%, 2%, 1%
		for(int i=-1; i<2; i+=2) {
			nx = x + dx[dir] + dx[(dir+i)==-1?3:(dir+i)%4];
			ny = y + dy[dir] + dy[(dir+i)==-1?3:(dir+i)%4];
			sand = map[x][y] * 10 / 100;
			moveSand(nx, ny, sand);
			totalSand -= sand;
			
			nx = x + dx[(dir+i)==-1?3:(dir+i)%4];
			ny = y + dy[(dir+i)==-1?3:(dir+i)%4];
			sand = map[x][y] * 7 / 100;
			moveSand(nx, ny, sand);
			totalSand -= sand;
			
			nx = x + (dx[(dir+i)==-1?3:(dir+i)%4] * 2);
			ny = y + (dy[(dir+i)==-1?3:(dir+i)%4] * 2);
			sand = map[x][y] * 2 / 100;
			moveSand(nx, ny, sand);
			totalSand -= sand;
			
			nx = x + dx[(dir+i)==-1?3:(dir+i)%4] + dx[dir+(i*2)==-1?3:dir+(i*2)==-2?2:(dir+(i*2))%4];
			ny = y + dy[(dir+i)==-1?3:(dir+i)%4] + dy[dir+(i*2)==-1?3:dir+(i*2)==-2?2:(dir+(i*2))%4];
			sand = map[x][y] * 1 / 100;
			moveSand(nx, ny, sand);
			totalSand -= sand;
		}
		// 알파
		nx = x + dx[dir];
		ny = y + dy[dir];
		moveSand(nx, ny, totalSand);
	}
	
	static void moveSand(int nx, int ny, int sand) {
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) ans += sand;
		else map[nx][ny] += sand;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		tornado();
	}
}