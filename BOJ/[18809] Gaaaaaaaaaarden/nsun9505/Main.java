import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] isVisited;
	static char[][] color;
	static int[][] dist;
	static Queue<Element> queue = new LinkedList<>();
	static ArrayList<Pos> position = new ArrayList<>();
	static int N, M, R, G;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		color = new char[N][M];
		isVisited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					position.add(new Pos(i, j));
			}
		}
		solution(0, 0, 0, new ArrayList<Element>());
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	public static void solution(int gCnt, int rCnt, int idx, ArrayList<Element> list) {
		if(gCnt == G && rCnt == R) {
			if(gCnt + rCnt > 10)
				return;
			
			queue.clear();
			for(int i=0; i<N; i++) {
				Arrays.fill(dist[i], 0);
				Arrays.fill(isVisited[i], false);
				Arrays.fill(color[i], '-');
			}
			
			int cnt = 0;
			for(Element elem : list) {
				queue.offer(elem);
				isVisited[elem.row][elem.col] = true;
				color[elem.row][elem.col] = elem.color;
			}
			
			while(!queue.isEmpty()) {
				Element elem = queue.poll();
				if(color[elem.row][elem.col]== 'F' )
					continue;
				
				for(int dir = 0; dir < 4; dir++) {
					int nx = elem.row + dx[dir];
					int ny = elem.col + dy[dir];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					
					if(map[nx][ny] == 0)
						continue;
					
					if(isVisited[nx][ny]) {
						if(color[nx][ny] == 'F')
							continue;
						if(color[nx][ny] != elem.color  && (elem.dist + 1) == dist[nx][ny]) {
							cnt++;
							color[nx][ny] = 'F';
						}
						continue;
					}
					
					queue.offer(new Element(nx, ny, elem.color, elem.dist + 1));
					isVisited[nx][ny] = true;
					dist[nx][ny] = elem.dist + 1;
					color[nx][ny] = elem.color;
				}
			}
			
			ans = Math.max(ans, cnt);
			return;
		}
		
		if(idx >= position.size())
			return;
		Pos pos = position.get(idx);
		Element elem = new Element(pos.row, pos.col, 'G', 0);
		list.add(elem);
		solution(gCnt+1, rCnt, idx+1, list);
		elem.color = 'R';
		solution(gCnt, rCnt+1, idx+1, list);
		list.remove(elem);
		solution(gCnt, rCnt, idx+1, list);
		
	}
	
	
	static class Element{
		int row;
		int col;
		char color;
		int dist;
		public Element(int row, int col, char color, int dist) {
			this.row = row;
			this.col = col;
			this.color = color;
			this.dist = dist;
		}
	}
	
	static class Pos{
		int row;
		int col;
		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
		
	}
}