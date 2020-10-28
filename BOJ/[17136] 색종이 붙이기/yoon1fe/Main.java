import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map = new int [10][10];
	static int[] paper = {0, 5, 5, 5, 5, 5}; 
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		input();
		
		dfs(0, 0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	public static void dfs(int y, int x, int cnt) {
		if (y == 9 && x > 9) {
			answer = Math.min(answer, cnt);
			return;
		}

		if (answer <= cnt) return;

		if (x == 10) {
            dfs(y + 1, 0, cnt);  return;
        }
		
		if (map[y][x] == 1) {
			for (int l = 5; l > 0; l--) {
				if (paper[l] == 0 || !check(y, x, l)) continue;

				action(y, x, l, 0);
				dfs(y, x + 1, cnt + 1);
				action(y, x, l, 1);
			}
		} else dfs(y, x + 1, cnt);
		
	}
	
	public static boolean isIn(int y, int x) {
		if(0 <= y && y < 10 && 0 <= x && x < 10) return true;
		else return false;
	}
	
	public static boolean check(int y, int x, int length) {
		for(int i = y; i < y + length; i++) {
			for(int j = x; j < x + length; j++) {
				if(!isIn(i, j) || map[i][j] == 0) return false;
			}
		}

		return true;
	}
	
	public static void action(int y, int x, int length, int action) {
		paper[length] = action == 0 ? paper[length] - 1: paper[length] + 1;
		for(int i = y; i < y + length; i++) {
			for(int j = x; j < x + length; j++) {
				map[i][j] = action;
			}
		}
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 10; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
}