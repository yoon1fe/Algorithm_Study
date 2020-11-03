import java.util.Scanner;
public class week10_모노미노도미노 {
	static int[][] blueArea = new int[4][6];
	static int[][] greenArea = new int[6][4];
	static int answer = 0;
	
	static void moveBlock(int t, int x, int y) {
		int nx=0, ny=0;
		// 초록색 보드 블럭 쌓기
		for(int i=1; i<6; i++) {
			nx = i;
			if(greenArea[nx][y] != 0) { nx--; break; }
			if(t == 2 && greenArea[nx][y+1] != 0)
				if(greenArea[nx][y] == 0) { nx--; break; }
		}
		if(t == 3) nx--;
		greenArea[nx][y] = t;
		if(t == 2) greenArea[nx][y+1] = t;
		else if(t == 3) greenArea[nx+1][y] = t;
		// 파란색 보드 블럭 쌓기
		for(int i=1; i<6; i++) {
			ny = i;
			if(blueArea[x][ny] != 0) { ny--; break; }
			if(t == 3 && blueArea[x+1][ny] != 0)
				if(blueArea[x][ny] == 0) { ny--; break; }
		}
		if(t == 2) ny--;
		blueArea[x][ny] = t;
		if(t == 3) blueArea[x+1][ny] = t;
		else if(t == 2) blueArea[x][ny+1] = t;
	}
	
	static boolean removeBlock() {
		boolean flag = false;
		boolean gf1=false, bf1=false;
		int gs=0, bs=0;
		for(int i=0; i<6; i++) {
			boolean gf2 = false;
			boolean bf2 = false;
			for(int j=0; j<4; j++) 
				if(greenArea[i][j]==0) {gf2=true; break;}
			for(int j=0; j<4; j++) 
				if(blueArea[j][i]==0) {bf2=true; break;}
			if(!gf2) { // 초록색 보드 한줄 지우기
				gf1=true; 
				gs = i;
				for(int j=0; j<4; j++) greenArea[i][j]=0;
				answer++;
			}
			if(!bf2) { // 파란색 보드 한줄 지우기
				bf1=true;
				bs = i;
				for(int j=0; j<4; j++) blueArea[j][i]=0;
				answer++;
			}
		}
		if(gf1) { // 초록색 보드 남은 블럭 밑으로 내리기
			for(int i=gs-1; i>=0; i--) {
				for(int j=0; j<4; j++) {
					int nx=i;
					if(greenArea[i][j]==1 || greenArea[i][j]==3) {
						while(true) {
							nx++;
							if(nx>5 || greenArea[nx][j]!=0) {nx--; break;}
						}
						if(nx==i) continue;
						greenArea[nx][j] = greenArea[i][j];
						greenArea[i][j] = 0;
					} else if(greenArea[i][j] == 2) {
						while(true) {
							nx++;
							if(nx>5) { nx--; break; }
							if(greenArea[nx][j] != 0) { nx--; break; }
							if(greenArea[nx][j+1] != 0)
								if(greenArea[nx][j] == 0) { nx--; break; }
						}
						greenArea[nx][j] = 2; greenArea[nx][j+1] = 2;
						greenArea[i][j] = 0; greenArea[i][j+1] = 0;
					}
				}
			}
			flag = true;
		}
		if(bf1) { // 파란색 보드 남은 블럭 밑으로 내리기
			for(int i=bs-1; i>=0; i--) {
				for(int j=0; j<4; j++) {
					int ny=i;
					if(blueArea[j][i]==1 || blueArea[j][i]==2) {
						while(true) {
							ny++;
							if(ny>5 || blueArea[j][ny]!=0) {ny--; break;}
						}
						if(ny==i) continue;
						blueArea[j][ny] = blueArea[j][i];
						blueArea[j][i] = 0;
					} else if(blueArea[j][i]==3) {
						while(true) {
							ny++;
							if(ny>5) { ny--; break; }
							if(blueArea[j][ny] != 0) { ny--; break; }
							if(blueArea[j+1][ny] != 0)
								if(blueArea[j][ny] == 0) { ny--; break; }
						}
						blueArea[j][ny] = 3; blueArea[j+1][ny] = 3;
						blueArea[j][i] = 0; blueArea[j+1][i] = 0;
					}
				}
			}
			flag = true;
		}
		return flag;
	}
	
	static void checkArea() {
		int gl=0, bl=0;
		for(int i=0; i<=1; i++) // 초록색 보드 연한 부분 검사
			for(int j=0; j<4; j++) 
				if(greenArea[i][j]!=0) {gl++; break;}
		for(int i=0; i<=1; i++) // 파란색 보드 연한 부분 검사
			for(int j=0; j<4; j++)
				if(blueArea[j][i]!=0) {bl++; break;}
		if(gl!=0) { // 초록색 보드 연한 부분 라인 수만큼 밑으로 내리기
			for(int i=5; i>=6-gl; i--) 
				for(int j=0; j<4; j++) 
					greenArea[i][j] = 0;
			for(int i=5-gl; i>=0; i--)
				for(int j=0; j<4; j++) {
					greenArea[i+gl][j] = greenArea[i][j];
					greenArea[i][j] = 0;
				}
		}
		if(bl!=0) { // 파란색 보드 연한 부분 라인 수만큼 밑으로 내리기
			for(int i=5; i>=6-bl; i--)
				for(int j=0; j<4; j++)
					blueArea[j][i] = 0;
			for(int i=5-bl; i>=0; i--)
				for(int j=0; j<4; j++) {
					blueArea[j][i+bl] = blueArea[j][i];
					blueArea[j][i] = 0;
				}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int total = 0;
		for(int n=0; n<N; n++) {
			int t = sc.nextInt(); // 1:1칸짜리, 2:가로2칸짜리, 3:세로2칸짜리
			int x = sc.nextInt();
			int y = sc.nextInt();
			moveBlock(t,x,y);
			while(true) {if(!removeBlock()) break;}
			checkArea();
		}
		for(int i=0; i<6; i++) {
			for(int j=0; j<4; j++) {
				if(greenArea[i][j]!=0) total++;
				if(blueArea[j][i]!=0) total++;
			}
		}
		System.out.println(answer);
		System.out.println(total);
	}
}