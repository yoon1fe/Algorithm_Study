import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, Q;
	static int[][] map;
	static int[] order;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int remained, maxVal;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	public static void solve() {
		v = new boolean[1 << N][1 << N];
		
		// 파이어 스톰 Q 번 시행
		for(int i = 0; i < order.length; i++) {
			rotate(order[i]);
		}
		
		// 남아있는 얼음의 합
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				remained += map[i][j];
			}
		}
		
		// 가장 큰 덩어리가 차지하는 칸의 개수
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				if(map[i][j] == 0 || v[i][j] == true) continue;
				maxVal = Math.max(maxVal, bfs(new Dir(i, j)));
			}
		}
		
		System.out.println(remained + "\n" + maxVal);
	}
	
	public static int bfs(Dir s) {
		int sum = 1;
		Queue<Dir> q = new LinkedList<>();
		v[s.y][s.x] = true;
		
		q.offer(s);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] == true || map[next.y][next.x] == 0) continue;
				
				v[next.y][next.x] = true;
				q.offer(next);
				sum++;
			}
		}
		
		return sum;
	}

	public static void rotate(int L) {
		// L에 맞게 인덱스 구하기
		for(int i = 0; i < (1 << N); i += (1 << L)) {
			for(int j = 0; j < (1 << N); j += (1 << L)) {
				// 회전할 애들
				List<List<Integer>> list = new ArrayList<>();
				for(int y = i; y < i + (1 << L); y++) {
					List<Integer> temp = new ArrayList<>();
					for(int x = j; x < j + (1 << L); x++) {
						temp.add(map[y][x]);
					}
					list.add(temp);
				}
				
				// 회전
				for(int x = j + (1 << L) - 1, lIdx = 0; x >= j; x--, lIdx++) {
					for(int y = i, idx = 0; y < i + (1 << L); y++, idx++) {
						map[y][x] = list.get(lIdx).get(idx);
					}
				}
			}
		}
		
		// 인접한 칸의 얼음의 수가 2개 이하인 곳 찾아서 얼음양 줄이기
		int[][] tempMap = new int[1 << N][1 << N];
		
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				if(map[i][j] == 0) continue;
				int adjCnt = 0;
				
				for(int k = 0; k < 4; k++) {
					Dir next = new Dir(i + dy[k], j + dx[k]);
					adjCnt += check(next);
				}
				
				tempMap[i][j] = adjCnt <= 2 ? map[i][j] - 1 : map[i][j];
			}
		}
		
		map = tempMap;
	}

	public static boolean isIn(Dir cur) {
		if(0 <= cur.y && cur.y < (1 << N) && 0 <= cur.x && cur.x < (1 << N)) return true;
		else return false;
	}
	
	public static int check(Dir cur) {
		if(isIn(cur) && map[cur.y][cur.x] != 0) return 1;
		else return 0;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); Q = Integer.parseInt(st.nextToken());
		
		map = new int[1 << N][1 << N];
		order = new int[Q];
		
		for(int i = 0; i < (1 << N); i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < (1 << N); j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < Q; i++) order[i] = Integer.parseInt(st.nextToken());
	}
}