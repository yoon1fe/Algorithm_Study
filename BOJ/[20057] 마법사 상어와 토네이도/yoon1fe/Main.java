import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N;
	static int[][] map;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {-1, 0, 1, 0};
	static int[][][] spread = {
			{ { 0, 0, 2, 0, 0 }, { 0, 10, 7, 1, 0 }, { 5, 55, 0, 0, 0 }, { 0, 10, 7, 1, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 2, 7, 0, 7, 2 }, { 0, 10, 55, 10, 0 }, { 0, 0, 5, 0, 0 } },
			{ { 0, 0, 2, 0, 0 }, { 0, 1, 7, 10, 0 }, { 0, 0, 0, 55, 5 }, { 0, 1, 7, 10, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 5, 0, 0 }, { 0, 10, 55, 10, 0 }, { 2, 7, 0, 7, 2 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } } };

	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve(0));
	}
	
	public static int solve(int d) {
		int answer = 0, cnt = 0, n = 1, twoCnt = 0;
		
		Dir tornado = new Dir(N / 2, N / 2);
		
		while(tornado.y != 0 || tornado.x != 0) {
			cnt++;
			tornado.y += dy[d]; tornado.x += dx[d];
			
			answer += move(tornado, d); 
		
			if(cnt == n) {
				twoCnt++;
				if(twoCnt == 2) {
					n++;
					twoCnt = 0;
				}
				cnt = 0;
				d = (d + 1) % 4;
			}
		}
		
		return answer;
	}
	
	public static int move(Dir c, int d) {
		int out = 0;
		int sand = map[c.y][c.x];
		Dir next = new Dir(c.y, c.x);
		int nextSand = 0;
		
		int sum = 0;
		for(int i = -2; i < 3; i++) {
			for(int j = -2; j < 3; j++) {
				next = new Dir(c.y + i, c.x + j);
				nextSand = (int)((sand * spread[d][i + 2][j + 2]) / 100);
				sum = spread[d][i+2][j+2] != 55 ? sum + nextSand : sum;
				if(spread[d][i + 2][j + 2] == 55) continue;
				
				out += update(next, nextSand);
			}
		}
		
		next.y = c.y + dy[d]; next.x = c.x + dx[d];
		nextSand = sand - sum;
		out += update(next, nextSand);
		
		map[c.y][c.x] = 0;
		return out;
	}

	private static int update(Dir next, int nextSand) {
		if(!isIn(next)) return nextSand;
		else {
			map[next.y][next.x] += nextSand;
			return 0;
		}
	}

	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}

	public static void input() throws Exception {
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