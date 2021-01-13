import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class week21_연구소2 {
	static int N, M;
	static int[][] map;
	static List<int[]> virus;
	static int[] arr;
	static int[] result;
	static int answer = Integer.MAX_VALUE;
	static boolean f = false;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void makeComb(int begin, int cnt) {
		if(cnt == result.length) {
			int time = spreadVirus();
			if(time != -1) {
				answer = Math.min(answer, time);
				f = true;
			}
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			result[cnt] = arr[i];
			makeComb(i+1, cnt+1);
		}
	}
	
	static int spreadVirus() {
		int cnt = 0;
		int[][] temp = new int[N][N];
		for(int i=0; i<N; i++) System.arraycopy(map[i], 0, temp[i], 0, N);
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[N][N];
		for(int i=0; i<result.length; i++) {
			int[] t = virus.get(result[i]);
			q.offer(new int[] {t[0], t[1], 0});
			v[t[0]][t[1]] = true;
			temp[t[0]][t[1]] = 3;
		}
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			cnt = p[2];
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(temp[nx][ny]==1 || v[nx][ny]) continue;
				q.offer(new int[] {nx, ny, p[2]+1});
				v[nx][ny] = true;
				temp[nx][ny] = 3;
			}
		}
		
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(temp[i][j]==0 || temp[i][j]==2) return -1;
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] {i, j});
			}
		}
		arr = new int[virus.size()];
		result = new int[M];
		for(int i=0; i<arr.length; i++) arr[i] = i;
		makeComb(0, 0);
		if(f) System.out.println(answer);
		else System.out.println(-1);
	}
}