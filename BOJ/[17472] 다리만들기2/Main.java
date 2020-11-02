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
	
	static int N, M;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int[][] map;
	static boolean [][] v;
	static List<Map<Integer, Integer>> graph = new ArrayList<>();
	
	static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < M) return true;
		else return false;
	}
	
	public static void makeIslandNo(Dir s, int no){
		Queue<Dir> q = new LinkedList<>();
		q.offer(s);
		v[s.y][s.x] = true;
		map[s.y][s.x] = no;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0 ; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] == true || map[next.y][next.x] == 0) continue;
				
				v[next.y][next.x] = true;
				map[next.y][next.x] = no; 
				q.offer(next);
			}
		}
	}
	
	public static void makeBridge(Dir s, int curNo, int dir, int len) {
		if(map[s.y][s.x] != 0 && map[s.y][s.x] != curNo) {
			if(len == 2) return;
			if(graph.get(curNo).containsKey(map[s.y][s.x])) {
				int minDist = Math.min(graph.get(curNo).get(map[s.y][s.x]), len - 1);
				graph.get(curNo).replace(map[s.y][s.x], minDist);
			}
			else graph.get(curNo).put(map[s.y][s.x], len - 1);
			return;
		}
		
		Dir next = new Dir(s.y + dy[dir], s.x + dx[dir]);
		if(!isIn(next) || map[next.y][next.x] == curNo) return;
		makeBridge(next, curNo, dir, len + 1);
	}
	
	public static int prim() {
		boolean[] visited = new boolean[graph.size()];
		int[] minEdge = new int[graph.size()];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		int minVertex;
		int min, result = 0;
		minEdge[1] = 0;
		
		for(int c = 0; c < graph.size() - 1; c++) {
			min = Integer.MAX_VALUE;
			minVertex = 1;
			
			for(int i = 1; i < graph.size(); i++) {
				if(!visited[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			result += min;
			visited[minVertex] = true;
			
			for(Integer to : graph.get(minVertex).keySet()) {
				int weight = graph.get(minVertex).get(to);
				if(!visited[to] && weight < minEdge[to]) {
					minEdge[to] = weight;
				}
			}
		}
		
		int falseCnt = 0;
		for(int i = 1; i < visited.length; i++) {
			if(!visited[i]) falseCnt++;
		}
		
		return falseCnt == 0 ? result : -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 번호 매기기
		int no = 1;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !v[i][j]) {
					makeIslandNo(new Dir(i, j), no++);
				}
			}
		}
		
		for(int i = 0; i < no; i++) graph.add(new HashMap<>());
		
		// 섬 사이에 놓을 수 있는 다리의 최소 길이 구하기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					for(int k = 0; k < 4; k++) {
						makeBridge(new Dir(i, j), map[i][j], k, 0);
					}
				}
			}
		}
		
		// MST 만들기
		System.out.println(prim());
	}
}