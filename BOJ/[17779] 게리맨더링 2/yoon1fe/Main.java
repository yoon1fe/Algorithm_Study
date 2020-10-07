import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int total;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				total += map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 2; j < N; j++) {
				int x = i, y = j;
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 < N; d2++) {
						if(x + d1 + d2 > N) continue;
						if(0 >= y - d1 || y + d2 > N) continue;
						answer = Math.min(answer, getPopulation(x, y, d1, d2));
					}
				}
			}
		}
		
		System.out.println(answer);
	}

	public static int getPopulation(int x, int y, int d1, int d2) {
		int[] area = new int[5];
		boolean[][] boundary = new boolean[N+1][N+1];
		
		for(int i = 0; i <= d1; i++){
			boundary[x+i][y-i] = true;
		}
		for(int i = 0; i <= d2; i++){
			boundary[x+i][y+i] = true;
		}
		for(int i = 0; i <= d2; i++){
			boundary[x+d1+i][y-d1+i] = true;
		}
		for(int i = 0; i <= d1; i++){
			boundary[x+d2+i][y+d2-i] = true;
		}
		
		// 1번 구역
		for(int i = 1; i < x + d1; i++) {
			for(int j = 1; j <= y; j++) {
				if(boundary[i][j]) break;
				area[0] += map[i][j];
			}
		}
		// 2번 구역
		for(int i = 1; i <= x + d2; i++) {
			for(int j = N; j > y; j--) {
				if(boundary[i][j]) break;
				area[1] += map[i][j];
			}
		}
		// 3번 구역
		for(int i = x + d1; i <= N; i++) {
			for(int j = 1; j < y - d1 + d2; j++) {
				if(boundary[i][j]) break;
				area[2] += map[i][j];
			}
		}
		// 4번 구역
		for(int i = x + d2 + 1; i <= N; i++) {
			for(int j = N; j >= y - d1 + d2; j--) {
				if(boundary[i][j]) break;
				area[3] += map[i][j];
			}
		}
		area[4] = total - Arrays.stream(area).sum();
		
		return Arrays.stream(area).max().getAsInt() - Arrays.stream(area).min().getAsInt();
	}
	
}