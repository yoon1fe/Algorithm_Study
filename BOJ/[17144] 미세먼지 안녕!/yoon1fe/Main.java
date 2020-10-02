package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static boolean[] selected;
	static int[][] map;
	static int R, C, T;
	static List<Dir> dust = new ArrayList<>();
	static Dir airCleaner;
	
	public static boolean isIn(Dir c) {
		if(0<= c.y && c.y < R && 0 <= c.x && c.x < C) return true;
		else return false;
	}
	
	public static int[][] spread() {
		int[][] temp = new int[R][C];
		for(Dir d : dust) {
			int origin = map[d.y][d.x]; 
			int spreadNum = (origin / 5);
			
			for(int i = 0 ; i < 4; i++) {
				Dir next = new Dir(d.y + dy[i], d.x + dx[i]);
				if(!isIn(next)) continue;
				if(next.x == 0 && (next.y == airCleaner.y || next.y == airCleaner.y-1)) continue;
				temp[next.y][next.x] +=  spreadNum;
				temp[d.y][d.x] -= spreadNum;
			}
			temp[d.y][d.x] += origin; 
		}
		return temp;
	}
	
	public static int[][] clean(int[][] temp) {
		for(int i = airCleaner.y-2; i > 0; i--) temp[i][0] = temp[i-1][0];
		for(int i = 0; i < C - 1; i++) temp[0][i] = temp[0][i+1];
		for(int i = 0; i < airCleaner.y-1; i++) temp[i][C-1] = temp[i+1][C-1];
		for(int i = C-1; i > 1; i--) temp[airCleaner.y-1][i] = temp[airCleaner.y-1][i-1];
		temp[airCleaner.y-1][1] = 0;
		
		for(int i = airCleaner.y+1; i < R - 1; i++) temp[i][0] = temp[i+1][0];
		for(int i = 0; i < C - 1; i++) temp[R-1][i] = temp[R-1][i+1];
		for(int i = R-1; i > airCleaner.y; i--) temp[i][C-1] = temp[i-1][C-1];
		for(int i = C-1; i > 1; i--) temp[airCleaner.y][i] = temp[airCleaner.y][i-1];
		temp[airCleaner.y][1] = 0;
		
		return temp;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					dust.add(new Dir(i, j));
				}
				if(map[i][j] == -1) airCleaner = new Dir(i, j);			//[i-1][j], [i][j]
			}
		}
		
		for(int time = 0; time < T; time++) {
			// 1: 미세먼지 확산 후 청소
			map = clean(spread());
			dust.clear();
			// 2: dust, map 업데이트
			for(int i = 0 ; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] > 0) dust.add(new Dir(i, j));
				}
			}
		}
		
		int answer = 0;
		for(Dir d : dust) answer+= map[d.y][d.x];
		System.out.println(answer);
	}
}