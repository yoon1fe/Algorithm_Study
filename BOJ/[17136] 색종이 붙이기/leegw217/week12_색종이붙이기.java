import java.util.Arrays;
import java.util.Scanner;
public class week12_색종이붙이기 {
	static int[][] map = new int[10][10];
	static int[] paper = new int[6];
	static int answer = Integer.MAX_VALUE;
	
	static void dfs(int x, int y, int cnt) {
		if(x >= 9 && y > 9) { // 모든 1에 색종이 다 붙임
			answer = Math.min(answer, cnt);
			return;
		}
		
		if(cnt >= answer) return; // 지금까지 붙인 색종이 수가 최소보다 크면 종료
		
		if(y == 10) {dfs(x+1, 0, cnt); return;} // y가 맵 밖이면 밑으로 한칸 내려서 돌리기
		
		if(map[x][y] == 0) { // 0이면 다음칸 검사
			dfs(x, y+1, cnt);
		} else if(map[x][y] == 1) {
			for(int p=5; p>=1; p--) {
				if(paper[p]>0 && canPut(x,y,p)) {
					putPaper(x,y,p,0);
					paper[p]--;
					dfs(x, y+1, cnt+1);
					// 백트래킹
					putPaper(x,y,p,1);
					paper[p]++;
				}
			}
		}
	}
	
	static boolean canPut(int x, int y, int p) { // 색종이 붙일 수 있는지 확인
		for(int i=x; i<x+p; i++) {
			for(int j=y; j<y+p; j++) {
				if(i>9 || j>9) return false;
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	static void putPaper(int x, int y, int p, int s) { // 색종이 붙이기
		for(int i=x; i<x+p; i++)
			for(int j=y; j<y+p; j++)
				map[i][j] = s;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				map[i][j] = sc.nextInt();
		Arrays.fill(paper, 5);
		dfs(0,0,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
}