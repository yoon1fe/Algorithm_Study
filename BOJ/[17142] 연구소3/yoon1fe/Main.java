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
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static List<Dir> virus = new ArrayList<>();
	static boolean[] selectedVirus;
	static int emptySpace;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new Dir(i, j));
				else if(map[i][j] == 0) emptySpace++;
			}
		}
		
		selectedVirus = new boolean[virus.size()];
		comb(0, 0);
		
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}

	public static void comb(int cnt, int idx) {
		if(cnt == M) {
			answer = Math.min(answer, spreadVirus());
			return;
		}
		
		for(int i = idx; i < virus.size(); i++) {
			selectedVirus[i] = true;
			comb(cnt + 1, i + 1);
			selectedVirus[i] = false;
		}
	}

	public static int spreadVirus() {
		int maxDay = 0;
		int remains = emptySpace;
		Queue<Dir> q = new LinkedList<>();
		int[][] v = new int[N][N];
		
		for(int i = 0; i < virus.size(); i++) {
			if(selectedVirus[i]) q.offer(virus.get(i));
			v[virus.get(i).y][virus.get(i).x] = selectedVirus[i] == true ? 1 : -1;
		}
		
		while(!q.isEmpty()) {
			if(remains == 0) break;
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || map[next.y][next.x] == 1 || v[next.y][next.x] > 0) continue;
				remains = map[next.y][next.x] == 0 ? remains - 1 : remains;
				v[next.y][next.x] = v[cur.y][cur.x] + 1;
				maxDay = Math.max(maxDay, v[next.y][next.x]);
				q.offer(next);
			}
		}
		
		return remains == 0 ? (maxDay == 0 ? 0 : maxDay - 1) : Integer.MAX_VALUE;
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
}
