package week07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
public class week07_감시 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int R, C;
	static List<CCTV> cctvList;
	static int cctvCnt;
	static int min = Integer.MAX_VALUE;
	
	static class CCTV{
		int type;
		int x, y;
		int dir;
		CCTV(int type, int x, int y, int dir){
			this.type = type;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static void makeDir(int cnt) { // cctv 방향 순열 구하기
		if(cnt == cctvCnt) {
			int result = findSafe();
			if(min > result) min = result;
			return;
		}
		int limit = cctvList.get(cnt).type == 2?2:4;
		
		if(cctvList.get(cnt).type == 5) 
			makeDir(cnt+1);
		else {
			for(int i=0; i<limit; i++) {
				cctvList.get(cnt).dir = i;
				makeDir(cnt+1);
			}
		}
	}
	
	static int findSafe() {
		int answer = 0;
		int[][] temp = new int[R][C];
		for(int i=0; i<R; i++) System.arraycopy(map[i], 0, temp[i], 0, C);
		for(int i=0; i<cctvCnt; i++) {
			CCTV cctv = cctvList.get(i);
			int nx = cctv.x;
			int ny = cctv.y;
			int dir = cctv.dir;
			int type = cctv.type;
			drawLine(temp, nx, ny, dir);
			if(type == 2 || type == 5) { // 반대 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				if(dir == 0) dir = 2;
				else dir = 3;
				drawLine(temp, nx, ny, dir);
			}
			if(type == 3 || type == 4 || type == 5) { // 오른쪽 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				dir += 1;
				if(dir == 4) dir = 0;
				drawLine(temp, nx, ny, dir);
			}
			if(type == 4 || type == 5) { // 왼쪽 방향 선긋기
				nx = cctv.x; ny = cctv.y;
				dir = cctv.dir;
				dir -= 1;
				if(dir == -1) dir = 3;
				drawLine(temp, nx, ny, dir);
			}
		}
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(temp[i][j] == 0) answer++;
		return answer;
	}
	
	static void drawLine(int[][] temp, int nx, int ny, int dir) {
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			if(nx<0||nx>=R||ny<0||ny>=C) break;
			if(temp[nx][ny] == 6) break;
			if(temp[nx][ny] != 0) continue;
			temp[nx][ny] = -1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cctvList = new ArrayList<CCTV>();
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(sc.nextLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6)
					cctvList.add(new CCTV(map[i][j], i, j, 0));
			}
		}
		cctvCnt = cctvList.size();
		makeDir(0);
		System.out.println(min);
	}
}