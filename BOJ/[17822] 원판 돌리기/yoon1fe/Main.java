package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;					// y번 원판의 x번 자리
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M, T, total, totalCnt;
	static List<Integer>[] circles;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); T = Integer.parseInt(st.nextToken());
		
		circles = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			circles[i] = new ArrayList<>();
			for(int j = 0; j < M; j++) {
				circles[i].add(Integer.parseInt(st.nextToken()));
				total += circles[i].get(j);
				totalCnt++;
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); int d = Integer.parseInt(st.nextToken()); int k = Integer.parseInt(st.nextToken());
			
			rotate(x, d, k);
			remove();
		}
		
		System.out.println(total);
	}
	
	public static void rotate(int x, int d, int k) {
		for(int i = x; i <= N; i += x) {
			List<Integer> temp = new ArrayList<>();
			if(d == 0) {
				temp.addAll(circles[i].subList(M - k, M));
				temp.addAll(circles[i].subList(0, M - k));
			}else {
				temp.addAll(circles[i].subList(k, M));
				temp.addAll(circles[i].subList(0, k));
			}
			circles[i].clear();
			circles[i].addAll(temp);
		}
	}

	public static void remove() {
		boolean[][] v = new boolean[N+1][M];
		boolean isDelete = false;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(v[i][j] || circles[i].get(j) == 0) continue;
				isDelete = isDelete | bfs(new Dir(i, j), v);
			}
		}
		
		if (!isDelete) {
			double avg = (double)total / totalCnt;

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (circles[i].get(j) == 0) continue;
					if (circles[i].get(j) > avg) {
						circles[i].set(j, circles[i].get(j) - 1);
						total--;
					}
					else if (circles[i].get(j) < avg) {
						circles[i].set(j, circles[i].get(j) + 1);
						total++;
					}
				}
			}
		}
		
	}

	public static boolean bfs(Dir s, boolean[][] v) {
		Queue<Dir> q = new LinkedList<>();
		int[] dx = {1, -1};
		int cn = circles[s.y].get(s.x);
		int cnt = 1;
		
		q.offer(s);
		v[s.y][s.x] = true;
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			int ny, nx;
			
			// 같은 원판
			ny = cur.y;
			for(int i = 0; i < 2; i++) {
				nx = (cur.x + dx[i] + M) % M;
				if(v[ny][nx]) continue;
				if(circles[ny].get(nx) == cn) {
					circles[ny].set(nx, 0);
					total -= cn;
					totalCnt--;
					cnt++;
					v[ny][nx] = true; 
					q.offer(new Dir(ny, nx));
				}
			}
			
			// 같은 위치
			nx = cur.x;
			for(int i = 0; i < 2; i++) {
				ny = cur.y + dx[i];
				if(ny < 1 || ny > N) continue;
				if(v[ny][nx]) continue;
				if(circles[ny].get(nx) == cn) {
					circles[ny].set(nx, 0);
					total -= cn;
					totalCnt--;
					cnt++;
					v[ny][nx] = true; 
					q.offer(new Dir(ny, nx));
				}
			}
		}
		
		if(cnt != 1) {
			circles[s.y].set(s.x, 0);
			total -= cn; totalCnt--;
			return true;
		}else return false;
	}
}