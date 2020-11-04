import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week11_어른상어 {
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static class Shark {
		int x, y;
		int dir;
		boolean alive;
		Shark(int x, int y, int dir, boolean alive){
			this.x = x; this.y = y;
			this.dir = dir; this.alive = alive;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Shark[] shark = new Shark[M+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int shk = Integer.parseInt(st.nextToken());
				if(shk != 0)
					shark[shk] = new Shark(i,j,0,true);
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) 
			shark[i].dir = Integer.parseInt(st.nextToken());
		int[][][] priority = new int[M+1][5][4];
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int d=0; d<4; d++) 
					priority[i][j][d] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] smell = new int[N][N];
		Queue<int[]> smellq = new LinkedList<int[]>();
		loop:
		for(int t=1; t<=1000; t++) {
			int size = smellq.size();
			int[][] sharkBoard = new int[N][N];
			for(int s=1; s<=M; s++) {
				if(!shark[s].alive) continue;
				// 상어 냄새 뿌리기
				smell[shark[s].x][shark[s].y] = s;
				smellq.offer(new int[] {K-1,shark[s].x,shark[s].y});
			}
			for(int s=1; s<=M; s++) {
				if(!shark[s].alive) continue;
				boolean f = false;
				// 인접 칸 중 냄새가 없는 칸으로 이동
				for(int d=0; d<4; d++) {
					int dir = priority[s][shark[s].dir][d];
					int nx = shark[s].x + dx[dir];
					int ny = shark[s].y + dy[dir];
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					if(smell[nx][ny] != 0) continue;
					f = true;
					// 상어가 이동한 위치에 다른 상어가 있을 때
					if(sharkBoard[nx][ny] != 0) {
						if(s > sharkBoard[nx][ny]) { // 나보다 번호 작은 상어가 있으면 잡아먹힘
							shark[s].alive = false;
							break;
						} else  // 나보다 번호 큰 상어가 있으면 잡아먹음
							shark[sharkBoard[nx][ny]].alive = false;
					} 
					sharkBoard[nx][ny] = s;
					shark[s].x = nx;
					shark[s].y = ny;
					shark[s].dir = dir;
					break;
				}
				if(!f) { // 인접 칸 중 냄새가 없는 칸이 없으면 자기 냄새 칸으로 이동
					for(int d=0; d<4; d++) {
						int dir = priority[s][shark[s].dir][d];
						int nx = shark[s].x + dx[dir];
						int ny = shark[s].y + dy[dir];
						if(nx<0||nx>=N||ny<0||ny>=N) continue;
						if(smell[nx][ny] == s) {
							sharkBoard[nx][ny] = s;
							smell[nx][ny] = -1;
							shark[s].x = nx;
							shark[s].y = ny;
							shark[s].dir = dir;
							break;
						}
					}
				}
			}
			// 냄새 최신화
			for(int sm=0; sm<size; sm++) {
				int[] p = smellq.poll();
				// 냄새가 시간이 지나서 사라지거나 상어가 다시 자기 냄새 위치로 돌아온 경우
				if(p[0]==1 || smell[p[1]][p[2]]==-1) { 
					smell[p[1]][p[2]] = 0;
					continue;
				}
				smellq.offer(new int[] {p[0]-1,p[1],p[2]});
			}
			// 1번 말고 다 죽었나 확인
			for(int i=2; i<=M; i++) 
				if(shark[i].alive) continue loop;
			System.out.println(t);
			System.exit(0);
		}
		System.out.println(-1);
	}
}