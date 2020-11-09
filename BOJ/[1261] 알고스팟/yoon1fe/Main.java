import java.io.*;
import java.util.*;

public class Main {
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		Queue<Dir> q = new LinkedList<>();
		int[][] v = new int[N][M];
		
		for(int i = 0; i < N; i++) Arrays.fill(v[i], Integer.MAX_VALUE);
		
		q.offer(new Dir(0, 0));
		v[0][0] = 0;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next)) continue;
				if(map[next.y][next.x] == 0 && v[next.y][next.x] > v[cur.y][cur.x] ) {
					v[next.y][next.x] = v[cur.y][cur.x];
					q.offer(next);
				}else if(map[next.y][next.x] == 1 && v[next.y][next.x] > v[cur.y][cur.x] + 1){
					v[next.y][next.x] = v[cur.y][cur.x] + 1;
					q.offer(next);
				}
			}
		}

		return v[N - 1][M - 1];
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
		else return false;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
	}
	
}