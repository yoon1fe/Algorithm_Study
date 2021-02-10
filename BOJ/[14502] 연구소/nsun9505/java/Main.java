import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] isVisited;
	static Queue<Element> queue = new LinkedList<>();
	static ArrayList<Element> emptyList = new ArrayList<>();
	static ArrayList<Element> virusList = new ArrayList<>();
	static int emptyCnt = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0)
					emptyList.add(new Element(i, j));
				else if(map[i][j] == 2)
					virusList.add(new Element(i, j));
			}
		}
		
		emptyCnt = emptyList.size();
		
		DFS(0, 0);
		
		sb.append(ans);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void DFS(int cnt, int idx) {
		if(cnt == 3) {
			int ret = BFS();
			ans = Math.max(ret, ans);
			return;
		}
		
		if(idx >= emptyList.size())
			return;
		
		Element elem = emptyList.get(idx);
		map[elem.row][elem.col] = 1;
		DFS(cnt+1, idx+1);
		
		map[elem.row][elem.col] = 0;
		DFS(cnt, idx+1);
	}
	
	public static int BFS() {
		int ret = 0;
		for(int i=0; i<N; i++)
			Arrays.fill(isVisited[i], false);
		queue.clear();
		for(Element virus : virusList) {
			queue.offer(virus);
			isVisited[virus.row][virus.col] = true; 
		}
		
		while(!queue.isEmpty()) {
			Element virus = queue.poll();
			
			for(int dir = 0; dir<4; dir++) {
				int nx = virus.row + dx[dir];
				int ny = virus.col + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(isVisited[nx][ny] || map[nx][ny] == 1)
					continue;
				
				ret++;
				queue.offer(new Element(nx, ny));
				isVisited[nx][ny] = true;
			}
		}
		
		ret = emptyCnt - 3 - ret;
		return ret;
	}
	
	static class Element{
		int row;
		int col;
		
		Element(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}