import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class week02_연구소 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> virus = new ArrayList<int[]>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int max = 0;
	
	static int bfs(int[][] m) {
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0; i<virus.size(); i++) {
			q.offer(virus.get(i));
			visited[virus.get(i)[0]][virus.get(i)[1]] = true;
		}
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny] || m[nx][ny]==1) continue;
				
				q.offer(new int[] {nx,ny});
				visited[nx][ny] = true;
				m[nx][ny] = 2;
			}
		}
		return countSafe(m);
	}
	
	static int countSafe(int[][] m) {
		int cnt=0;
		for(int i=0; i<N; i++) 
			for(int j=0; j<M; j++) 
				if(m[i][j] == 0) cnt++;			
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] {i,j});
			}
		}
		for(int i1=0; i1<N; i1++) {
			for(int j1=0; j1<M; j1++) { 
				if(map[i1][j1] == 0) {
					for(int i2=(j1+1==M?i1+1:i1); i2<N; i2++) {
						for(int j2=(i2==i1?j1+1:0); j2<M; j2++) {
							if(map[i2][j2] == 0) {
								for(int i3=(j2+1==M?i2+1:i2); i3<N; i3++) {
									for(int j3=(i3==i2?j2+1:0); j3<M; j3++) {
										if(map[i3][j3]==0) {
											visited = new boolean[N][M];
											int[][] tempMap = new int[N][M];
											for(int k=0; k<N; k++)
												System.arraycopy(map[k], 0, tempMap[k], 0, map[k].length);
											tempMap[i1][j1] = 1;
											tempMap[i2][j2] = 1;
											tempMap[i3][j3] = 1;
											int safe = bfs(tempMap);
											if(max < safe) max = safe;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(max);
	}
}