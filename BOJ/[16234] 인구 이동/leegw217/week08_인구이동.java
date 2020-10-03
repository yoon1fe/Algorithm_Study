package week08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class week08_인구이동 {
	static boolean checkMove(int[][] m, int l, int r) {
		int n = m.length;
		List<int[]> answer = new ArrayList<int[]>();
		int[][] visited = new int[n][n];
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] != 0) continue;
				int cnt = 1;
				idx++;
				Queue<int[]> q = new LinkedList<int[]>();
				visited[i][j] = idx;
				q.offer(new int[] {i,j});
				int sum = m[i][j];
				
				while(!q.isEmpty()) {
					int[] p = q.poll();
					for(int d=0; d<4; d++) {
						int nx = p[0] + dx[d];
						int ny = p[1] + dy[d];
						if(nx<0||nx>=n||ny<0||ny>=n) continue;
						if(visited[nx][ny] != 0) continue;
						int cha = Math.abs(m[p[0]][p[1]] - m[nx][ny]);
						if(l<=cha && cha<=r) {
							visited[nx][ny] = idx;
							cnt++;
							sum += m[nx][ny];
							q.offer(new int[] {nx,ny});
						}
					}
				}
				if(cnt == 1) {
					visited[i][j] = -1;
					idx--;
				} else answer.add(new int[] {cnt, sum});
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] > 0) {
					int[] t = answer.get(visited[i][j]-1);
					m[i][j] = t[1]/t[0];
				}
			}
		}
		if(answer.size() == 0) return false;
		else return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = sc.nextInt();
		
		while(true) {
			if(!checkMove(map, L, R)) break;
			answer ++;
		}
		System.out.println(answer);
	}
}