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
	
	static class Fish{
		Dir loc;
		int d;
		Fish(int y, int x, int d){
			this.loc = new Dir(y, x); this.d = d;
		}
	}
	
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Fish> fishes = new TreeMap<>();
		int[][] map = new int[4][4];
		
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[i][j] = no; fishes.put(no, new Fish(i, j, d - 1));
			}
		}
		
		Fish shark = new Fish(0, 0, fishes.get(map[0][0]).d);
		solve(new Dir(0, 0), 0, map, shark, fishes);
		
		System.out.println(answer);
	}
	
	
	public static void solve(Dir start, int tempAns, int[][] map, Fish shark, Map<Integer, Fish> fishes) {
		if(!canGo(start, map)) {
			answer = Math.max(answer, tempAns);
			return;
		}
		
		if(fishes.get(map[start.y][start.x]) != null) {
			shark.d = fishes.get(map[start.y][start.x]).d;
			tempAns += map[start.y][start.x];
			fishes.remove(map[start.y][start.x]);
		}
		else return;
		shark.loc.y = start.y; shark.loc.x = start.x;
		map[start.y][start.x] = 20;	// 상어
		
		fishMoves(map, shark, fishes);
		
		map[start.y][start.x] = 0;

		for (int i = 1; i < 4; i++) {
			int[][] tempMap = new int[4][4];
			Map<Integer, Fish> tempFishes = new TreeMap<>();
			Fish tempShark = new Fish(shark.loc.y, shark.loc.x, shark.d);
			for (int j = 0; j < 4; j++) System.arraycopy(map[j], 0, tempMap[j], 0, 4);
			for (int no : fishes.keySet()) tempFishes.put(no, new Fish(fishes.get(no).loc.y, fishes.get(no).loc.x, fishes.get(no).d));

			solve(new Dir(start.y + dy[shark.d] * i, start.x + dx[shark.d] * i), tempAns, tempMap, tempShark, tempFishes);

			continue;
		}
		
	}
	
	public static void fishMoves(int[][] map, Fish shark, Map<Integer, Fish> fishes) {
		outer:
		for(int no : fishes.keySet()) {
			Dir next = new Dir(fishes.get(no).loc.y + dy[fishes.get(no).d], fishes.get(no).loc.x + dx[fishes.get(no).d]);
			
			boolean flag = false;
			for(int i = 0; i < 8; i++) {
				if(canGo(next, map)) {
					flag = true; break;
				}
				fishes.get(no).d = (fishes.get(no).d + 1) % 8;
				next.y = fishes.get(no).loc.y + dy[fishes.get(no).d]; next.x = fishes.get(no).loc.x + dx[fishes.get(no).d];
			}
			if(!flag) continue outer;
			
			if(fishes.get(map[next.y][next.x]) != null) {
				map[fishes.get(no).loc.y][fishes.get(no).loc.x] = map[next.y][next.x];
				fishes.replace(map[next.y][next.x], new Fish(fishes.get(no).loc.y, fishes.get(no).loc.x, fishes.get(map[next.y][next.x]).d));
			}
			else map[fishes.get(no).loc.y][fishes.get(no).loc.x] = 0;
			
			fishes.replace(no, new Fish(next.y, next.x, fishes.get(no).d));
			map[next.y][next.x] = no;
		}
	}
	
	public static boolean canGo(Dir cur, int[][] map) {
		if(0 <= cur.y && cur.y < 4 && 0 <= cur.x && cur.x < 4 && map[cur.y][cur.x] != 20 ) return true;
		else return false;
	}
}