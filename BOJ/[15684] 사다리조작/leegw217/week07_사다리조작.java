package week07;

import java.util.Scanner;
public class week07_사다리조작 {
	static int N, M, H;
	static int[][] line;
	static void makeLine(int[][] li, int cnt, int limit) {
		if(cnt == limit) { // 사다리 추가한 개수 제한 , 0,1,2,3 개
			if(play(li)) {
				System.out.println(limit);
				System.exit(0);
			}
			return;
		}
		for(int i=1; i<N; i++) { // 사다리 추가 순열
			for(int j=1; j<=H; j++) {
				if(li[i][j] == 1) continue;
				if(li[i-1][j]==1 || li[i+1][j]==1) continue;
				li[i][j] = 1;
				makeLine(li, cnt+1, limit);
				li[i][j] = 0;
			}
		}
	}
	
	static boolean play(int[][] li) { // 사다리타기
		for(int i=1; i<=N; i++) {
			int curLine = i;
			int curH = 1;
			while(true) {
				if(curH > H) break;
				if(li[curLine-1][curH] == 1) curLine -= 1; // 왼쪽으로 이동
				else if(li[curLine][curH] == 1) curLine += 1; // 오른쪽으로 이동
				curH += 1; // 밑으로 이동
			}
			if(curLine != i) return false; // 최종 도착번호가 출발번호와 같아야함
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		line = new int[N+1][H+1];
		for(int i=0; i<M; i++) {
			int h = sc.nextInt();
			int n = sc.nextInt();
			line[n][h] = 1;
		}
		for(int i=0; i<=3; i++) {
			int[][] temp = new int[N+1][H+1];
			for(int j=0; j<N+1; j++) System.arraycopy(line[j], 0, temp[j], 0, H+1);
			makeLine(temp, 0, i);
		}
		System.out.println(-1);
	}
}