package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	
	static class Fish implements Comparable<Fish> {
		Dir loc;
		int size;
		
		public Fish(Dir loc, int size) {
			this.loc = loc; this.size = size;
		}

		public int compareTo(Fish o) {
			if(this.loc.y == o.loc.y) return this.loc.x - o.loc.x;
			else return this.loc.y - o.loc.y;
		}
	}
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int[][] map;
	static Fish shark;
	static int eatCnt;
	static int time;
	
	static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	static boolean bfs(Fish shark) {
		Queue<Fish> q = new LinkedList<>(); 
		PriorityQueue<Fish> fishes = new PriorityQueue<>();
		int[][] v = new int[N][N];
		q.offer(shark);
		v[shark.loc.y][shark.loc.x] = 1;
		
		// bfs 돌면서 pq 사이즈가 1 이상이 되면 그 중에서 peek 에 있는 쪽으로 가자
		int curDist = 401;		// 먹을 수 있는 물고기를 찾은 경우 거기까지의 거리만 체크
		
		while(!q.isEmpty()) {
			Fish cur = q.poll();
			if(v[cur.loc.y][cur.loc.x] >= curDist) continue;  
			for(int i = 0; i < 4; i++) {
				Fish next = new Fish(new Dir(cur.loc.y + dy[i], cur.loc.x + dx[i]), cur.size);
				if(!isIn(next.loc) || v[next.loc.y][next.loc.x] != 0) continue;
				if(map[next.loc.y][next.loc.x] > next.size ) continue;
				
				if(map[next.loc.y][next.loc.x] != 0 && map[next.loc.y][next.loc.x] < next.size ) {
					fishes.offer(new Fish(next.loc, map[next.loc.y][next.loc.x]));
					curDist = v[cur.loc.y][cur.loc.x] + 1;
				}
				v[next.loc.y][next.loc.x] = v[cur.loc.y][cur.loc.x] + 1;
				q.offer(next);
			}
		}
		// 먹을 수 있는 물고기들이 생긴 경우
		if(!fishes.isEmpty()) {
			Fish fishToEat = fishes.poll();
			time += v[fishToEat.loc.y][fishToEat.loc.x] - 1;		// 가는데 걸린 시간 더해 줌
			eatCnt++;
			if(eatCnt == shark.size) {
				shark.size++;
				eatCnt = 0;
			}
			shark.loc = fishToEat.loc;
			map[shark.loc.y][shark.loc.x] = 0;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Fish(new Dir(i, j), 2);
				}
			}
		}
		
		while(true) {
//			System.out.println("shark loc(" + shark.loc.y + ", " + shark.loc.x + ") size: " + shark.size + ", time: " + time);
			if(!bfs(shark)) 
				break;
		}
		
		System.out.println(time);
	}
}