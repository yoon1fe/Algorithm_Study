import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week13_다리만들기2 {
	static int N, M;
	static int mapNum = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[][] adjMatrix;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void bfs(int x, int y) {
		mapNum++;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x,y});
		visited[x][y] = true;
		map[x][y] = mapNum;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int dir=0; dir<4; dir++) {
				int nx = p[0] + dx[dir];
				int ny = p[1] + dy[dir];
				
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny] || map[nx][ny] == 0) continue;
				
				q.offer(new int[] {nx,ny});
				visited[nx][ny] = true;
				map[nx][ny] = mapNum;
			}
		}
	}

	static void makeBridge() {
		for(int x=0; x<N; x++) {
			for(int y=0; y<M; y++) {
				if(map[x][y] == 0) continue;
				int mapN = map[x][y];
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					if(map[nx][ny] != 0) continue;
					int len = 1;
					while(true) {
						nx += dx[d];
						ny += dy[d];
						if(nx<0||nx>=N||ny<0||ny>=M) break;
						if(map[nx][ny] == mapN) break;
						if(map[nx][ny]>0 && map[nx][ny]!=mapN) {
							if(len == 1) break; // 길이 1인 다리는 안됨
							if(adjMatrix[mapN][map[nx][ny]] != 0) { // 기존에 연결된 다리가 있으면
								if(adjMatrix[mapN][map[nx][ny]] > len) // 새로 연결한 다리가 더짧으면 교체
									adjMatrix[mapN][map[nx][ny]] = adjMatrix[map[nx][ny]][mapN] = len;
							} else { // 연결된 다리가 없으면
								adjMatrix[mapN][map[nx][ny]] = adjMatrix[map[nx][ny]][mapN] = len;
							}
							break;
						}
						len++;
					}
				}
			}
		}
	}
	
	static int makeMST() {
		int[] minEdge = new int[mapNum+1];
		boolean[] v = new boolean[mapNum+1];
		int result = 0;
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		
		for(int c=0; c<mapNum; c++) {
			int min = Integer.MAX_VALUE;
			int minNo = 1;
			
			for(int i=1; i<=mapNum; i++) {
				if(!v[i] && min>minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			v[minNo] = true;
			result += min;
			
			for(int i=1; i<=mapNum; i++) {
				if(!v[i] && adjMatrix[minNo][i]>0 && minEdge[i]>adjMatrix[minNo][i])
					minEdge[i] = adjMatrix[minNo][i];
			}
		}
		
		for(int k=1; k<=mapNum; k++) {
			if(v[k] == false)
				return -1;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || visited[i][j]) continue;
				bfs(i,j);
			}
		}
		adjMatrix = new int[mapNum+1][mapNum+1];
		makeBridge();
		System.out.println(makeMST());
	}
}