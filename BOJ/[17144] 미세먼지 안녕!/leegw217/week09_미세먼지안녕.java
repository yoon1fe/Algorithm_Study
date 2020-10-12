package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class week09_미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static List<Integer> robot = new ArrayList<Integer>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void dustDiffusion() {
		int[][] dustMap = new int[R][C];
		for(int x=0; x<R; x++) {
			for(int y=0; y<C; y++) {
				if(map[x][y] == -1) continue;
				if(map[x][y] != 0) {
					for(int dir=0; dir<4; dir++) {
						int nx = x + dx[dir];
						int ny = y + dy[dir];
						if(nx<0||nx>=R||ny<0||ny>=C) continue;
						if(map[nx][ny] == -1) continue;
						dustMap[nx][ny] += map[x][y] / 5;
						dustMap[x][y] -= map[x][y] / 5;
					}
				}
			}
		}
		for(int i=0; i<R; i++) 
			for(int j=0; j<C; j++) 
				map[i][j] += dustMap[i][j];
	}
	
	static void rotateMap() {
		int[][] tempMap = new int[R][C];
		for(int i=0; i<R; i++)
			System.arraycopy(map[i], 0, tempMap[i], 0, map[i].length);
		for(int c=0; c<C-1; c++) {
			if(c==0) tempMap[robot.get(0)][c+1] = 0;
			else tempMap[robot.get(0)][c+1] = map[robot.get(0)][c];
		}
		for(int r=robot.get(0); r>0; r--) 
			tempMap[r-1][C-1] = map[r][C-1];
		for(int c=C-1; c>0; c--)
			tempMap[0][c-1] = map[0][c];
		for(int r=0; r<robot.get(0)-1; r++)
			tempMap[r+1][0] = map[r][0];
		
		for(int c=0; c<C-1; c++) {
			if(c==0) tempMap[robot.get(1)][c+1] = 0;
			else tempMap[robot.get(1)][c+1] = map[robot.get(1)][c];
		}
		for(int r=R-1; r>robot.get(1)+1; r--) 
			tempMap[r-1][0] = map[r][0];
		for(int c=C-1; c>0; c--)
			tempMap[R-1][c-1] = map[R-1][c];
		for(int r=robot.get(1); r<R-1; r++)
			tempMap[r+1][C-1] = map[r][C-1];
		
		for(int i=0; i<R; i++)
			System.arraycopy(tempMap[i], 0, map[i], 0, tempMap[i].length);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) robot.add(i);
			}
		}
		for(int t=0; t<T; t++) {
			dustDiffusion();
			rotateMap();
		}
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}
}