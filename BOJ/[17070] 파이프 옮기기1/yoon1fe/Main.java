package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, answer;
	static int[][] map;
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		input();
	
		dfs(0, 0, 1);	// status 0: 가로		1: 세로	2: 대각선
		System.out.println(answer);
	}
	
	public static void dfs(int status, int y, int x) {
		if(y == N - 1 && x == N - 1) {
			answer++;
			return;
		}
		
		switch(status) {
		case 0:
			for(int i = 0; i < 2; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if(i == 0 && available(ny, nx)) dfs(i, ny, nx);
				if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
			}
			break;
		case 1:
			for(int i = 0; i < 3; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if(i == 0 && available(ny, nx)) dfs(i, ny, nx);
				if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
				if(i == 2 && available(ny, nx)) dfs(i, ny, nx);
			}
			break;
		case 2:
			for(int i = 1; i < 3; i++) {
				int ny = y + dy[i], nx = x + dx[i];
				if(i == 1 && checkDiagonal(ny, nx)) dfs(i, ny, nx);
				if(i == 2 && available(ny, nx)) dfs(i, ny, nx);
			}
			break;
		}
	}
	
	public static boolean available(int y, int x) {
		if(0 <= y && y < N && 0 <= x && x < N && map[y][x] == 0) return true;
		else return false;
	}
	
	public static boolean checkDiagonal(int y, int x) {
		if(available(y -1, x) && available(y, x) && available(y, x - 1)) return true;
		else return false;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
	}
}