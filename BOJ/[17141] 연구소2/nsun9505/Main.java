import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Virus> virusList = new ArrayList<>();
	static int[][] board;
	static boolean[] isUsed;
	static int ans = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2)
					virusList.add(new Virus(i, j));
			}
		}
		isUsed = new boolean[virusList.size()];
		solution(0, new ArrayList<>());
		if(ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
	
	public static void solution(int idx, List<Virus> list) {
		if (list.size() == M) {
			int ret = BFS(list);
			if (ret != -1)
				ans = ret < ans ? ret : ans;
			return;
		}

		for(int i=idx; i<virusList.size(); i++) {
			if(isUsed[i])
				continue;
			
			isUsed[i] = true;
			list.add(virusList.get(i));
			solution(idx+1, list);
			list.remove(list.size()-1);
			isUsed[i] = false;
		}
	}
	
	public static int BFS(List<Virus> list) {
		int ret = 0;
		Queue<Virus> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][N];
		for(int i=0; i<list.size(); i++) {
			Virus v = list.get(i);
			queue.offer(v);
			isVisited[v.row][v.col] = true; 
		}
		
		while(!queue.isEmpty()) {
			Virus virus = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = virus.row + dx[dir];
				int ny = virus.col + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if(isVisited[nx][ny] || board[nx][ny] == 1)
					continue;
				
				isVisited[nx][ny] = true;
				queue.offer(new Virus(nx, ny, virus.time + 1));
				if(ret < virus.time + 1)
					ret = virus.time + 1;
			}
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(board[i][j] == 0 && !isVisited[i][j])
					return -1;
		
		return ret;
	}
	
	static class Virus{
		int row;
		int col;
		int time;
		
		public Virus(int row, int col) {
			this.row = row;
			this.col = col;
			this.time = 0;
		}
		
		public Virus(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
}