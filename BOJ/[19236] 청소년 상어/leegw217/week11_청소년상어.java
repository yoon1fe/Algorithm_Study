import java.util.Scanner;
public class week11_청소년상어 {
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int max = Integer.MIN_VALUE;
	
	static class Fish {
		int x, y;
		int dir;
		boolean alive;
		Fish(int x, int y, int dir, boolean alive){
			this.x = x; this.y = y;
			this.dir = dir; this.alive = alive;
		}
	}
	
	static void eatFish(int[][] tb, Fish[] tf, int x, int y, int sdir, int sum) {
		max = Math.max(max, sum);
		
		for(int i=1; i<=3; i++) {
			int nx = x + dx[sdir]*i;
			int ny = y + dy[sdir]*i;
			if(nx<0||nx>=4||ny<0||ny>=4) continue;
			if(tb[nx][ny] == 0) continue;
			// 보드와 물고기리스트 복사본 만들기
			int[][] tempboard = new int[4][4];
			for(int t=0; t<4; t++) System.arraycopy(tb[t], 0, tempboard[t], 0, 4);
			Fish[] tempfish = new Fish[17];
			for(int t=1; t<17; t++) tempfish[t] = new Fish(tf[t].x,tf[t].y,tf[t].dir,tf[t].alive);
			// 물고기 먹기
			int idx = tempboard[nx][ny];
			int dir = tempfish[idx].dir;
			tempboard[x][y] = 0;
			tempboard[nx][ny] = -1;
			tempfish[idx].alive = false;
			// 물고기 이동 시키고 상어 이동
			moveFish(tempboard, tempfish);
			eatFish(tempboard, tempfish, nx, ny, dir, sum+idx);
		}
	}
	
	static void moveFish(int[][] tb, Fish[] tf) {
		int i = 1;
		int dcnt = 0;
		while(true) {
			if(i >= 17 || dcnt == 8) break;
			if(!tf[i].alive) {i++; continue;}
			int nx = tf[i].x + dx[tf[i].dir];
			int ny = tf[i].y + dy[tf[i].dir];
			if(nx<0||nx>=4||ny<0||ny>=4||tb[nx][ny]==-1) {
				tf[i].dir = tf[i].dir+1==9?1:tf[i].dir+1;
				dcnt++; // 방향 한바퀴 다돌아서 검사했는데 못움직이면 종료
				continue;
			}
			if(tb[nx][ny] >= 0) {
				int idx = tb[nx][ny];
				int tx = tf[i].x;
				int ty = tf[i].y;
				// 바꿀 위치에 원래 위치 물고기 넣어주기
				tf[i].x = nx; tf[i].y = ny;
				tb[nx][ny] = i;
				// 원래 위치에 바꿀 위치 물고기 넣어주기
				if(idx >= 1) {
					tf[idx].x = tx;
					tf[idx].y = ty;
				}
				tb[tx][ty] = idx;
				i++;
				dcnt=0;
			} 
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[4][4];
		Fish[] fishList = new Fish[17];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				int idx = sc.nextInt();
				int dir = sc.nextInt();
				board[i][j] = idx;
				fishList[idx] = new Fish(i, j, dir, true);
			}
		}
		// [0,0]칸 물고기 먹고 시작
		int idx = board[0][0];
		fishList[idx].alive = false;
		int d = fishList[idx].dir;
		board[0][0] = -1;
		moveFish(board, fishList);
		eatFish(board, fishList, 0, 0, d, idx);
		System.out.println(max);
	}
}
