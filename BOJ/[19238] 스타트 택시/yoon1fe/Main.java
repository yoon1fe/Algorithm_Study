package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir implements Comparable<Dir>{
		int y, x;

		public Dir(int y, int x) {
			this.y = y; this.x = x;
		}

		public int compareTo(Dir o) {
			if(this.y == o.y) return this.x - o.x;
			return this.y - o.y;
		}
	}
	
	static int N, M, fuel, cnt;
	static List<Integer>[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static Dir taxi;
	
	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}
	
	public static int solve() {
		
		for(int i = 0; i < M; i++) {
			// 택시 -> 승객 구하기
			
			Dir nextCustomer = selectCustomer(taxi);
			if(nextCustomer == null) return -1;
			Collections.sort(map[nextCustomer.y][nextCustomer.x], Collections.reverseOrder());
			int custNo = map[nextCustomer.y][nextCustomer.x].get(0);

			taxi.y = nextCustomer.y; taxi.x = nextCustomer.x;
			map[taxi.y][taxi.x].remove((Integer)custNo);
			
			
			// 목적지로 이동
			Dir nextDest = goToDest(nextCustomer, custNo * -1);
			
			if(nextDest == null) return -1;
			taxi.y = nextDest.y; taxi.x = nextDest.x;
			map[taxi.y][taxi.x].remove((Integer)(-1 * custNo));
			
		}
		
		return fuel;
	}
	
	public static Dir goToDest(Dir s, int no) {
		
		Queue<Dir> q = new LinkedList<>();
		int[][] v = new int[N][N];
		Dir dest = null;
		q.offer(s);
		v[s.y][s.x] = 1;
		
		outer:
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!canGo(next) || v[next.y][next.x] != 0) continue;
				
				v[next.y][next.x] = v[cur.y][cur.x] + 1;
				q.offer(next);
				
				if(map[next.y][next.x].size() == 0 ) continue;
				if(map[next.y][next.x].contains(no) == true) {
					dest = new Dir(next.y, next.x); break outer;
				}
			}
		}
		
		if(dest == null) return null;
		fuel -= (v[dest.y][dest.x] - 1); 
		fuel = fuel < 0 ? -1 : fuel + (v[dest.y][dest.x] - 1) * 2; 
		return fuel < 0 ? null : dest;
	}
	
	public static Dir selectCustomer(Dir s) {

		PriorityQueue<Dir> pq = new PriorityQueue<>();
		Queue<Dir> q = new LinkedList<>();
		
		int[][] v = new int[N][N];
		int minDist = Integer.MAX_VALUE;
		
		q.offer(s);
		v[s.y][s.x] = 1;
		
		if(map[s.y][s.x].size() != 0) {
			Collections.sort(map[s.y][s.x], Collections.reverseOrder());
			if(map[s.y][s.x].get(0) > 1) return s;
		}
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!canGo(next) || v[next.y][next.x] != 0) continue;
				
				v[next.y][next.x] = v[cur.y][cur.x] + 1;
				q.offer(next);
				if(map[next.y][next.x].size() == 0) continue;
				Collections.sort(map[next.y][next.x], Collections.reverseOrder());
				if(map[next.y][next.x].get(0) > 1 && v[next.y][next.x] <= minDist ) {
					minDist = Math.min(minDist, v[next.y][next.x]); pq.offer(next);
				}
			}
		}
		if(pq.size() == 0) return null;
		fuel -= (v[pq.peek().y][pq.peek().x] - 1);
		return fuel < 1 ? null : pq.peek();
	}

	public static boolean canGo(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N && map[c.y][c.x].contains(1) == false ) return true;
		else return false;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); fuel = Integer.parseInt(st.nextToken());
		map = new List[N][N];
		cnt = M;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
				int t = Integer.parseInt(st.nextToken());
				if(t == 0) continue;
				map[i][j].add(t);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int y = Integer.parseInt(st.nextToken()) - 1; int x = Integer.parseInt(st.nextToken()) - 1;
		taxi = new Dir(y, x);
		
		for(int i = 2; i < 2 + M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(i);		// 승객 번호 (2번부터 시작)
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(i * -1);	// 목적지 번호 (승객 번호 * -1)
		}
		
	}
}