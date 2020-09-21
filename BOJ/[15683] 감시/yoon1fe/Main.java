package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static int[] cctvDir;
	static List<Dir> cctv = new ArrayList<>();
	static int[][] map;
	static int N, M;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int answer;
	
	static class Dir{
		int no;
		int y, x; 
		Dir(int no, int y, int x){
			this.no = no; this.y = y; this.x = x;
		}
	}
	
	public static void comb(int cnt) {
		if(cnt == cctv.size()) {
			setCCTV();
			return;
		}
		
		switch(cctv.get(cnt).no) {
		case 1:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 2:
			for(int i = 0; i < 2; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 3:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 4:
			for(int i = 0; i < 4; i++) {
				cctvDir[cnt] = i;
				comb(cnt + 1);
			}
			break;
		case 5:
			cctvDir[cnt] = 0;
			comb(cnt + 1);
		}
	}
	
	public static void setCCTV() {
		boolean[][] checked = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M; j++) {
				checked[i][j] = map[i][j] > 0 ? true : false;
			}
		}
		
		for(int i = 0; i < cctv.size(); i++) {
			Dir cur = cctv.get(i);
			switch(cur.no) {
			case 1:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); break;
				case 1: checkDown(checked, cur); break;
				case 2: checkLeft(checked, cur); break;
				case 3: checkRight(checked, cur); break;
				}
				break;
			case 2:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkDown(checked, cur); break;
				case 1: checkLeft(checked, cur); checkRight(checked, cur); break;
				}
				break;
			case 3:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkRight(checked, cur); break;
				case 1: checkUp(checked, cur); checkLeft(checked, cur); break;
				case 2: checkDown(checked, cur); checkRight(checked, cur); break;
				case 3: checkDown(checked, cur); checkLeft(checked, cur); break;
				}
				break;
				
			case 4:
				switch(cctvDir[i]) {
				case 0: checkUp(checked, cur); checkLeft(checked, cur); checkRight(checked, cur); break;
				case 1: checkUp(checked, cur); checkLeft(checked, cur); checkDown(checked, cur); break;
				case 2: checkDown(checked, cur); checkRight(checked, cur); checkUp(checked, cur); break;
				case 3: checkDown(checked, cur); checkLeft(checked, cur); checkRight(checked, cur); break;
				}
				
				break;
			case 5:
				checkUp(checked, cur);
				checkDown(checked, cur);
				checkLeft(checked, cur);
				checkRight(checked, cur);
			}
		}
		
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M; j++) {
				if(checked[i][j] == false) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}
	
	public static void checkUp(boolean[][] checked, Dir cur) {
		for(int i = cur.y - 1; i >= 0; i--) {
			if(map[i][cur.x] == 6 ) break;
			checked[i][cur.x] = true; 
		}
	}
	public static void checkDown(boolean[][] checked, Dir cur) {
		for(int i = cur.y + 1; i < N; i++) {
			if(map[i][cur.x] == 6 ) break;
			checked[i][cur.x] = true; 
		}
	}
	public static void checkLeft(boolean[][] checked, Dir cur) {
		for(int i = cur.x - 1; i >= 0; i--) {
			if(map[cur.y][i] == 6 ) break;
			checked[cur.y][i] = true; 
		}
	}
	public static void checkRight(boolean[][] checked, Dir cur) {
		for(int i = cur.x + 1; i < M; i++) {
			if(map[cur.y][i] == 6 ) break;
			checked[cur.y][i] = true; 
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(1<= map[i][j] && map[i][j] <= 5) cctv.add(new Dir(map[i][j], i, j)); 
				if(map[i][j] == 0) answer++;
			}
		}
		
		cctvDir = new int[cctv.size()];
		if(cctv.size() != 0) {
			answer = Integer.MAX_VALUE;
			comb(0);
		}

		System.out.println(answer);
	}
}
